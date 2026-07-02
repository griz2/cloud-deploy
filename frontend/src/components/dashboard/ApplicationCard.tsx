import type { Application } from "../../types/Application";

interface ApplicationCardProps {
  application: Application;
}

function ApplicationCard({ application }: ApplicationCardProps) {
  return (
    <div className="card">
      <h2>{application.name}</h2>

      <div className="card-row">
        <strong>Environment:</strong> {application.environment}
      </div>

      <div className="card-row">
        <strong>Status:</strong> {application.status}
      </div>

      <div className="card-row">
        <strong>Health:</strong> {application.health}
      </div>

      <div className="card-row">
        <strong>Repository:</strong>
      </div>

      <a
        href={application.repositoryUrl}
        target="_blank"
        rel="noopener noreferrer"
      >
        {application.repositoryUrl}
      </a>
    </div>
  );
}

export default ApplicationCard;