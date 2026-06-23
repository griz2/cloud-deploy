package com.clouddeploy.backend.controller;

import com.clouddeploy.backend.dto.CreateDeploymentRequest;
import com.clouddeploy.backend.dto.DeploymentResponse;
import com.clouddeploy.backend.service.DeploymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications/{applicationId}/deployments")
public class DeploymentController {

    private final DeploymentService deploymentService;

    public DeploymentController(DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeploymentResponse createDeployment(
            @PathVariable Long applicationId,
            @RequestBody CreateDeploymentRequest request) {

        return deploymentService.createDeployment(applicationId, request);
    }

    @GetMapping
    public List<DeploymentResponse> getDeployments(
            @PathVariable Long applicationId) {

        return deploymentService.getDeploymentsForApplication(applicationId);
    }
}