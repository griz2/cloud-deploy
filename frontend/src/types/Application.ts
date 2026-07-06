export interface Application {
  id: number;
  name: string;
  repositoryUrl: string;
  environment: string;
  healthCheckUrl: string;
  currentStatus: string;
  createdAt: string;
  updatedAt: string;
}