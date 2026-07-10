package com.clouddeploy.backend.repository;

import com.clouddeploy.backend.model.ApplicationEnvironmentVariable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationEnvironmentVariableRepository
        extends JpaRepository<ApplicationEnvironmentVariable, Long> {

    List<ApplicationEnvironmentVariable> findByApplicationId(Long applicationId);
}