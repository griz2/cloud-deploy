package com.clouddeploy.backend.model.enums;

public enum DeploymentEventType {

    BUILD_STARTED,
    BUILD_COMPLETED,

    TESTS_STARTED,
    TESTS_PASSED,
    TESTS_FAILED,

    DEPLOY_STARTED,
    DEPLOY_COMPLETED,
    DEPLOY_FAILED
}