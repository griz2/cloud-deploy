# CloudDeploy Database Design

## users

Represents a user of the CloudDeploy dashboard.

Fields:
- id
- email
- name
- created_at

## applications

Represents an application being tracked by CloudDeploy.

Fields:
- id
- user_id
- name
- repository_url
- environment
- health_check_url
- current_status
- created_at
- updated_at

Relationships:
- One user can have many applications.
- One application can have many deployments.
- One application can have many health checks.
- One application can have many log entries.

## deployments

Represents one deployment attempt for an application.

Fields:
- id
- application_id
- commit_sha
- branch
- status
- started_at
- finished_at
- created_at

Possible statuses:
- PENDING
- RUNNING
- SUCCESS
- FAILED
- CANCELLED

Relationships:
- One application can have many deployments.
- One deployment can have many deployment events.
- One deployment can have many log entries.

## deployment_events

Represents timestamped events during a deployment.

Fields:
- id
- deployment_id
- event_type
- message
- created_at

Example event types:
- BUILD_STARTED
- BUILD_COMPLETED
- TESTS_STARTED
- TESTS_PASSED
- DEPLOY_STARTED
- DEPLOY_COMPLETED
- DEPLOY_FAILED

## health_checks

Represents the result of checking whether an application is healthy.

Fields:
- id
- application_id
- status
- response_time_ms
- checked_at
- status_code
- error_message

Possible statuses:
- HEALTHY
- UNHEALTHY
- UNKNOWN

## log_entries

Represents logs connected to an application or deployment.

Fields:
- id
- application_id
- deployment_id
- log_level
- message
- source
- created_at

Possible log levels:
- INFO
- WARN
- ERROR
- DEBUG