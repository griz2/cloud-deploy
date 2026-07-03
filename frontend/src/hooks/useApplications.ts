import { useEffect, useState } from "react";
import { getApplications } from "../services/applicationService";
import type { Application } from "../types/Application";

export function useApplications() {
  const [applications, setApplications] = useState<Application[]>([]);
  const [loading, setLoading] = useState(true);

  async function refreshApplications() {
    try {
      const data = await getApplications();
      setApplications(data);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    refreshApplications();
  }, []);

  return {
    applications,
    loading,
    refreshApplications,
  };
}