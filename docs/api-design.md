# CloudDeploy API Design

## Purpose

This document defines the initial API contract for CloudDeploy V1.

CloudDeploy is a deployment tracking and monitoring platform that allows users to:

* Register applications
* Track deployments
* View deployment history
* Monitor application health
* View deployment and application logs

The API is designed around the following core entities:

* User
* Application
* Deployment
* DeploymentEvent
* HealthCheck
* LogEntry

---

# Core User Flow

## 1. Register Application

A user registers an application that CloudDeploy will track.

Example:

```json
{
  "name": "Document Generator",
  "repositoryUrl": "https://github.com/user/document-generator",
  "environment": "production",
  "healthCheckUrl": "https://api.example.com/health"
}
```

---

## 2. View Applications

Users can view all registered applications and their current status.

Example Dashboard View:

```text
Document Generator
Status: HEALTHY
Last Deployment: SUCCESS
```

---

## 3. Create Deployment

A deployment record is created whenever a deployment begins.

Example:

```json
{
  "branch": "main",
  "commitSha": "a1b2c3d4"
}
```

---

## 4. Track Deployment Events

Deployment progress is represented through deployment events.

Examples:

```text
BUILD_STARTED
BUILD_COMPLETED
TESTS_STARTED
TESTS_PASSED
DEPLOY_STARTED
DEPLOY_COMPLETED
```

---

## 5. Record Health Checks

CloudDeploy stores historical health check results.

Example:

```json
{
  "status": "HEALTHY",
  "responseTimeMs": 43,
  "statusCode": 200
}
```

---

## 6. Store Logs

CloudDeploy stores logs associated with either:

* An application
* A deployment

Examples:

```text
INFO Application Started
WARN Slow Response
ERROR Database Connection Failed
```

---

# API Endpoints

## Applications

### Create Application

```http
POST /api/applications
```

Request Body:

```json
{
  "name": "Document Generator",
  "repositoryUrl": "https://github.com/user/document-generator",
  "environment": "production",
  "healthCheckUrl": "https://api.example.com/health"
}
```

---

### Get All Applications

```http
GET /api/applications
```

---

### Get Application By ID

```http
GET /api/applications/{id}
```

---

### Update Application

```http
PUT /api/applications/{id}
```

---

### Delete Application

```http
DELETE /api/applications/{id}
```

---

# Deployments

### Create Deployment

```http
POST /api/applications/{applicationId}/deployments
```

Request Body:

```json
{
  "branch": "main",
  "commitSha": "a1b2c3d4"
}
```

---

### Get Deployments For Application

```http
GET /api/applications/{applicationId}/deployments
```

---

### Get Deployment By ID

```http
GET /api/deployments/{deploymentId}
```

---

# Deployment Events

### Create Deployment Event

```http
POST /api/deployments/{deploymentId}/events
```

Request Body:

```json
{
  "eventType": "BUILD_STARTED",
  "message": "Docker image build started"
}
```

---

### Get Deployment Events

```http
GET /api/deployments/{deploymentId}/events
```

---

# Health Checks

### Create Health Check

```http
POST /api/applications/{applicationId}/health-checks
```

Request Body:

```json
{
  "status": "HEALTHY",
  "responseTimeMs": 43,
  "statusCode": 200
}
```

---

### Get Health Check History

```http
GET /api/applications/{applicationId}/health-checks
```

---

# Logs

### Create Log Entry

```http
POST /api/applications/{applicationId}/logs
```

Request Body:

```json
{
  "logLevel": "INFO",
  "message": "Application started",
  "source": "backend"
}
```

---

### Get Application Logs

```http
GET /api/applications/{applicationId}/logs
```

---

# Status Enums

## Deployment Status

```text
PENDING
RUNNING
SUCCESS
FAILED
CANCELLED
```

---

## Health Status

```text
HEALTHY
UNHEALTHY
UNKNOWN
```

---

## Log Levels

```text
INFO
WARN
ERROR
DEBUG
```

---

# V1 Non-Goals

The following features are intentionally excluded from V1:

* Kubernetes
* Multi-cloud deployment
* Advanced alerting
* Billing
* Team management
* Complex RBAC
* GitHub OAuth
* Automatic repository deployment
* Log aggregation/search platform

These may be considered for future versions after the core platform is complete.

---

# V1 Success Criteria

A user can:

1. Register an application
2. View applications
3. Create deployment records
4. Track deployment history
5. View deployment events
6. View health check history
7. View application logs

The platform demonstrates:

* Spring Boot
* React
* PostgreSQL
* Docker
* GitHub Actions
* AWS
* Terraform
* Monitoring
* Logging
* CI/CD
* Backend system design
