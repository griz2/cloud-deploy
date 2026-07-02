import apiClient from "../api/apiClient";
import type { Application } from "../types/Application";

export async function getApplications(): Promise<Application[]> {
  const response = await apiClient.get<Application[]>("/applications");
  return response.data;
}