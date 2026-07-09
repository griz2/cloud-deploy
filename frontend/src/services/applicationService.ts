import apiClient from "../api/apiClient";
import type { Application } from "../types/Application";

export async function getApplications(): Promise<Application[]> {
  const response = await apiClient.get<Application[]>("/applications");
  return response.data;
}

export type CreateApplicationRequest = {
  name: string;
  repositoryUrl: string;
  dockerfilePath: string;
  environment: string;
  healthCheckUrl: string;
};

export async function createApplication(
  application: CreateApplicationRequest
): Promise<Application> {
  const response = await apiClient.post<Application>(
    "/applications",
    application
  );

  return response.data;
}