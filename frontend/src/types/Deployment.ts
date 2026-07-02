export interface Deployment {
  id: number;
  branch: string;
  commit: string;
  status: string;
  deployedAt: string;
}