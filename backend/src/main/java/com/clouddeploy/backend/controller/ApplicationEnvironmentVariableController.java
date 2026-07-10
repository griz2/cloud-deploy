package com.clouddeploy.backend.controller;

import com.clouddeploy.backend.model.ApplicationEnvironmentVariable;
import com.clouddeploy.backend.service.ApplicationEnvironmentVariableService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationEnvironmentVariableController {

    private final ApplicationEnvironmentVariableService environmentVariableService;

    public ApplicationEnvironmentVariableController(
            ApplicationEnvironmentVariableService environmentVariableService) {
        this.environmentVariableService = environmentVariableService;
    }

    @GetMapping("/applications/{applicationId}/environment-variables")
    public List<ApplicationEnvironmentVariable> getEnvironmentVariables(
            @PathVariable Long applicationId) {

        return environmentVariableService.getEnvironmentVariables(applicationId);
    }

    @PostMapping("/applications/{applicationId}/environment-variables")
    public ApplicationEnvironmentVariable createEnvironmentVariable(
            @PathVariable Long applicationId,
            @RequestBody CreateEnvironmentVariableRequest request) {

        return environmentVariableService.createEnvironmentVariable(
                applicationId,
                request.getKey(),
                request.getValue());
    }

    @DeleteMapping("/environment-variables/{id}")
    public void deleteEnvironmentVariable(@PathVariable Long id) {
        environmentVariableService.deleteEnvironmentVariable(id);
    }

    public static class CreateEnvironmentVariableRequest {

        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}