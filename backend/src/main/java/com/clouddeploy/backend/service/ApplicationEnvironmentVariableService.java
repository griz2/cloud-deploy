package com.clouddeploy.backend.service;

import com.clouddeploy.backend.model.Application;
import com.clouddeploy.backend.model.ApplicationEnvironmentVariable;
import com.clouddeploy.backend.repository.ApplicationEnvironmentVariableRepository;
import com.clouddeploy.backend.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationEnvironmentVariableService {

    private final ApplicationEnvironmentVariableRepository environmentVariableRepository;
    private final ApplicationRepository applicationRepository;

    public ApplicationEnvironmentVariableService(
            ApplicationEnvironmentVariableRepository environmentVariableRepository,
            ApplicationRepository applicationRepository) {

        this.environmentVariableRepository = environmentVariableRepository;
        this.applicationRepository = applicationRepository;
    }

    public List<ApplicationEnvironmentVariable> getEnvironmentVariables(
            Long applicationId) {

        return environmentVariableRepository.findByApplicationId(applicationId);
    }

    public ApplicationEnvironmentVariable createEnvironmentVariable(
            Long applicationId,
            String key,
            String value) {

        Application application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException("Application not found."));

        ApplicationEnvironmentVariable variable =
                new ApplicationEnvironmentVariable();

        variable.setApplication(application);
        variable.setVariableKey(key);
        variable.setVariableValue(value);

        return environmentVariableRepository.save(variable);
    }

    public void deleteEnvironmentVariable(Long id) {

        environmentVariableRepository.deleteById(id);

    }
}