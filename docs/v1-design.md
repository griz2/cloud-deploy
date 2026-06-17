# CloudDeploy V1 Design

## Project Summary

CloudDeploy is a cloud deployment and monitoring dashboard for containerized applications. It allows users to register applications, track deployments, view deployment status, monitor health checks, and review deployment logs.

The goal of V1 is not to build a full Heroku/Vercel replacement. The goal is to demonstrate backend engineering, DevOps fundamentals, CI/CD, Docker, AWS, Terraform, monitoring, and logging in a realistic but manageable portfolio project.

## V1 Core Features

- Register applications
- View registered applications
- Create deployment records
- Track deployment status
- Store deployment events
- Store basic log entries
- Track health check status
- Display all of this in a React dashboard

## Out of Scope for V1

- Kubernetes
- Multi-cloud deployment
- Real-time billing
- Enterprise teams/permissions
- Full GitHub OAuth integration
- Fully automated user-provided repo deployments
- Complex log search
- Advanced alerting

## Main Tech Stack

- Backend: Spring Boot
- Frontend: React
- Database: PostgreSQL
- Local environment: Docker Compose
- CI/CD: GitHub Actions
- Cloud: AWS
- Infrastructure as Code: Terraform
- Monitoring: Spring Boot Actuator, later Prometheus/Grafana or CloudWatch

## Main Entities

- User
- Application
- Deployment
- DeploymentEvent
- HealthCheck
- LogEntry

## V1 Success Criteria

By the end of V1, CloudDeploy should show that I can:

- Design backend APIs
- Model deployment-related data
- Build a full-stack dashboard
- Containerize services with Docker
- Use CI/CD with GitHub Actions
- Deploy to AWS
- Use Terraform for infrastructure
- Add basic monitoring and logging