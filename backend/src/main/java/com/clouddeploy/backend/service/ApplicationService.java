package com.clouddeploy.backend.service;

import com.clouddeploy.backend.dto.ApplicationResponse;
import com.clouddeploy.backend.dto.CreateApplicationRequest;
import com.clouddeploy.backend.model.Application;
import com.clouddeploy.backend.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public ApplicationResponse createApplication(CreateApplicationRequest request) {

        Application application = new Application();

        application.setName(request.getName());
        application.setRepositoryUrl(request.getRepositoryUrl());
        application.setDockerfilePath(request.getDockerfilePath());
        application.setEnvironment(request.getEnvironment());
        application.setHealthCheckUrl(request.getHealthCheckUrl());

        Application savedApplication = applicationRepository.save(application);

        return mapToResponse(savedApplication);
    }

    public List<ApplicationResponse> getAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ApplicationResponse mapToResponse(Application application) {

        ApplicationResponse response = new ApplicationResponse();

        response.setId(application.getId());
        response.setName(application.getName());
        response.setRepositoryUrl(application.getRepositoryUrl());
        response.setDockerfilePath(application.getDockerfilePath());
        response.setEnvironment(application.getEnvironment());
        response.setHealthCheckUrl(application.getHealthCheckUrl());
        response.setCurrentStatus(application.getCurrentStatus());
        response.setCreatedAt(application.getCreatedAt());
        response.setUpdatedAt(application.getUpdatedAt());

        return response;
    }
}