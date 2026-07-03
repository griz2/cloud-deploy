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

    public DeploymentEngine(
            DeploymentRepository deploymentRepository,
            DockerService dockerService) {

        this.deploymentRepository = deploymentRepository;
        this.dockerService = dockerService;
    }

    @Async
    public void executeDeployment(Deployment deployment) {

        deployment.setStatus(DeploymentStatus.RUNNING);
        deploymentRepository.save(deployment);

        try {

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