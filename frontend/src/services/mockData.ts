import type { Application } from "../types/Application";
import type { Deployment } from "../types/Deployment";
import type { Health } from "../types/Health";

export const application: Application = {
  id: 1,
  name: "CloudDeploy",
  environment: "Development",
  status: "Running",
  health: "UP",
  repositoryUrl: "https://github.com/griz2/cloud-deploy"
};

export const deployments: Deployment[] = [
  {
    id: 1,
    branch: "main",
    commit: "7a8f23b",
    status: "Success",
    deployedAt: "2 minutes ago"
  },
  {
    id: 2,
    branch: "main",
    commit: "3f9cd12",
    status: "Success",
    deployedAt: "1 hour ago"
  }
];

export const health: Health = {
  application: "UP",
  database: "Connected",
  api: "Reachable"
};