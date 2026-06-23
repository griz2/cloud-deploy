package com.clouddeploy.backend.repository;

import com.clouddeploy.backend.model.DeploymentEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeploymentEventRepository
        extends JpaRepository<DeploymentEvent, Long> {

    List<DeploymentEvent> findByDeploymentId(Long deploymentId);
}