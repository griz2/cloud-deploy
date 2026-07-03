package com.clouddeploy.backend.service;

import com.clouddeploy.backend.dto.CreateDeploymentRequest;
import com.clouddeploy.backend.dto.DeploymentResponse;
import com.clouddeploy.backend.model.Application;
import com.clouddeploy.backend.model.Deployment;
import com.clouddeploy.backend.repository.ApplicationRepository;
import com.clouddeploy.backend.repository.DeploymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeploymentService {

    private final DeploymentRepository deploymentRepository;
    private final ApplicationRepository applicationRepository;
    private final DeploymentEngine deploymentEngine;

    public DeploymentService(
            DeploymentRepository deploymentRepository,
            ApplicationRepository applicationRepository,
            DeploymentEngine deploymentEngine) {

        this.deploymentRepository = deploymentRepository;
        this.applicationRepository = applicationRepository;
        this.deploymentEngine = deploymentEngine;
    }

    public DeploymentResponse createDeployment(
            Long applicationId,
            CreateDeploymentRequest request) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        Deployment deployment = new Deployment();

        deployment.setCommitSha(request.getCommitSha());
        deployment.setBranch(request.getBranch());
        deployment.setApplication(application);

        Deployment savedDeployment = deploymentRepository.save(deployment);

        deploymentEngine.executeDeployment(savedDeployment);

        return mapToResponse(savedDeployment);
    }

    public List<DeploymentResponse> getDeploymentsForApplication(Long applicationId) {

        return deploymentRepository.findByApplicationId(applicationId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private DeploymentResponse mapToResponse(Deployment deployment) {

        DeploymentResponse response = new DeploymentResponse();

        response.setId(deployment.getId());
        response.setCommitSha(deployment.getCommitSha());
        response.setBranch(deployment.getBranch());
        response.setStatus(deployment.getStatus());
        response.setStartedAt(deployment.getStartedAt());
        response.setFinishedAt(deployment.getFinishedAt());
        response.setCreatedAt(deployment.getCreatedAt());
        response.setApplicationId(deployment.getApplication().getId());

        return response;
    }
}