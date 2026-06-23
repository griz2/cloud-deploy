package com.clouddeploy.backend.repository;

import com.clouddeploy.backend.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}