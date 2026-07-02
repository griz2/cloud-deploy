export interface Application {
  id: number;
  name: string;
  environment: string;
  status: string;
  health: string;
  repositoryUrl: string;
}