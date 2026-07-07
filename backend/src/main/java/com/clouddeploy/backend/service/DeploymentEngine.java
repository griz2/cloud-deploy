package com.clouddeploy.backend.service;

import com.clouddeploy.backend.model.Deployment;
import com.clouddeploy.backend.model.enums.DeploymentStatus;
import com.clouddeploy.backend.repository.DeploymentRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.time.LocalDateTime;

@Service
public class DeploymentEngine {

    private final DeploymentRepository deploymentRepository;
    private final DockerService dockerService;
    private final GitService gitService;
    private final DockerBuildService dockerBuildService;

    public DeploymentEngine(
            DeploymentRepository deploymentRepository,
            DockerService dockerService,
            GitService gitService,
            DockerBuildService dockerBuildService) {

        this.deploymentRepository = deploymentRepository;
        this.dockerService = dockerService;
        this.gitService = gitService;
        this.dockerBuildService = dockerBuildService;
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

            dockerBuildService.buildImage(
                workspace,
                deployment.getApplication().getDockerfilePath(),
                imageTag);

            deployment.setStatus(DeploymentStatus.SUCCESS);

        } catch (Exception e) {

            e.printStackTrace();

            deployment.setStatus(DeploymentStatus.FAILED);

        } finally {

            deployment.setFinishedAt(LocalDateTime.now());

            deploymentRepository.save(deployment);
        }
    }
}