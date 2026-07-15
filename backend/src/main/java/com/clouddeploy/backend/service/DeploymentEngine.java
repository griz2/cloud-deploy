package com.clouddeploy.backend.service;

import com.clouddeploy.backend.model.Deployment;
import com.clouddeploy.backend.model.ApplicationEnvironmentVariable;
import com.clouddeploy.backend.model.enums.DeploymentStatus;
import com.clouddeploy.backend.repository.DeploymentRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Service
public class DeploymentEngine {

    private final DeploymentRepository deploymentRepository;
    private final DockerService dockerService;
    private final GitService gitService;
    private final DockerBuildService dockerBuildService;
    private final DockerRunService dockerRunService;
    private final ApplicationEnvironmentVariableService environmentVariableService;

    public DeploymentEngine(
        DeploymentRepository deploymentRepository,
        DockerService dockerService,
        GitService gitService,
        DockerBuildService dockerBuildService,
        DockerRunService dockerRunService,
        ApplicationEnvironmentVariableService environmentVariableService) {

        this.deploymentRepository = deploymentRepository;
        this.dockerService = dockerService;
        this.gitService = gitService;
        this.dockerBuildService = dockerBuildService;
        this.dockerRunService = dockerRunService;
        this.environmentVariableService = environmentVariableService;
    }

    @Async
    public void executeDeployment(Deployment deployment) {

        deployment.setStatus(DeploymentStatus.RUNNING);
        deploymentRepository.save(deployment);

        try {

            dockerService.verifyDockerInstalled();

            Path workspace = gitService.cloneRepository(
                    deployment.getApplication().getRepositoryUrl());

            String imageTag =
                    "clouddeploy-" + deployment.getId();

            deployment.setImageTag(imageTag);

            dockerBuildService.buildImage(
                    workspace,
                    deployment.getApplication().getDockerfilePath(),
                    imageTag);

            List<ApplicationEnvironmentVariable> environmentVariables =
                    environmentVariableService.getEnvironmentVariables(
                            deployment.getApplication().getId());

            String containerId =
                    dockerRunService.runContainer(
                            imageTag,
                            environmentVariables);

            deployment.setContainerId(containerId);

            String containerName =
                    dockerRunService.getContainerName(containerId);

            deployment.setContainerName(containerName);

            deploymentRepository.save(deployment);

            System.out.println("Deployment container started: " + containerId);

            Thread.sleep(5000);

            boolean running =
                    dockerRunService.isContainerRunning(containerId);

            if (running) {

                deployment.setStatus(DeploymentStatus.SUCCESS);

            } else {

                deployment.setStatus(DeploymentStatus.FAILED);

            }

        } catch (Exception e) {

            e.printStackTrace();

            deployment.setStatus(DeploymentStatus.FAILED);

        } finally {

            deployment.setFinishedAt(LocalDateTime.now());

            deploymentRepository.save(deployment);
        }
    }
}