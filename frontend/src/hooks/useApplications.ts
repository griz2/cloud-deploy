import { useEffect, useState } from "react";
import { getApplications } from "../services/applicationService";
import type { Application } from "../types/Application";

export function useApplications() {
  const [applications, setApplications] = useState<Application[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function fetchApplications() {
      try {
        const data = await getApplications();
        setApplications(data);
      } finally {
        setLoading(false);
      }
    }

    fetchApplications();
  }, []);

  return {
    applications,
    loading,
  };
}