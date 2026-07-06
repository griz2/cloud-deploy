package com.clouddeploy.backend.service;

import com.clouddeploy.backend.model.Deployment;
import com.clouddeploy.backend.model.enums.DeploymentStatus;
import com.clouddeploy.backend.repository.DeploymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;

@Service
public class DeploymentEngine {

    private final DeploymentRepository deploymentRepository;
    private final DockerService dockerService;
    private final GitService gitService;

    public DeploymentEngine(
            DeploymentRepository deploymentRepository,
            DockerService dockerService,
            GitService gitService) {

        this.deploymentRepository = deploymentRepository;
        this.dockerService = dockerService;
        this.gitService = gitService;
    }

    @Async
    public void executeDeployment(Deployment deployment) {

        System.out.println(">>> EXECUTE DEPLOYMENT CALLED <<<");

        deployment.setStatus(DeploymentStatus.RUNNING);
        deploymentRepository.save(deployment);

        try {

            gitService.cloneRepository(
                    deployment.getApplication().getRepositoryUrl());

            dockerService.verifyDockerInstalled();

            deployment.setStatus(DeploymentStatus.SUCCESS);

        } catch (Exception e) {

            deployment.setStatus(DeploymentStatus.FAILED);

        } finally {

            deployment.setFinishedAt(LocalDateTime.now());

            deploymentRepository.save(deployment);
        }
    }
}