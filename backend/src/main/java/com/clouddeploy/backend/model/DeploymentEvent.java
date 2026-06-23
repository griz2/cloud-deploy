package com.clouddeploy.backend.model;

import com.clouddeploy.backend.model.enums.DeploymentEventType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "deployment_events")
public class DeploymentEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeploymentEventType eventType;

    @Column(nullable = false)
    private String message;

    private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "deployment_id")
    private Deployment deployment;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public DeploymentEvent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeploymentEventType getEventType() {
        return eventType;
    }

    public void setEventType(DeploymentEventType eventType) {
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Deployment getDeployment() {
        return deployment;
    }

    public void setDeployment(Deployment deployment) {
        this.deployment = deployment;
    }
}