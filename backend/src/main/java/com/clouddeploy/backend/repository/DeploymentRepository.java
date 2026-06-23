package com.clouddeploy.backend.repository;

import com.clouddeploy.backend.model.Deployment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeploymentRepository extends JpaRepository<Deployment, Long> {

    List<Deployment> findByApplicationId(Long applicationId);

}