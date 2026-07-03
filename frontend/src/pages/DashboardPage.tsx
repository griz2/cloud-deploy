import { useState } from "react";
import ApplicationCard from "../components/dashboard/ApplicationCard";
import NewApplicationModal from "../components/dashboard/NewApplicationModal";
import { useApplications } from "../hooks/useApplications";

function DashboardPage() {
  const {
    applications,
    loading,
    refreshApplications,
  } = useApplications();

  const [showNewApplicationModal, setShowNewApplicationModal] =
    useState(false);

  if (loading) {
    return <p>Loading applications...</p>;
  }

  return (
    <>
      <h1>Dashboard</h1>

      <button onClick={() => setShowNewApplicationModal(true)}>
        + New Application
      </button>

      <NewApplicationModal
        open={showNewApplicationModal}
        onClose={() => setShowNewApplicationModal(false)}
        onCreated={refreshApplications}
      />

      {applications.length === 0 ? (
        <p>No applications have been registered yet.</p>
      ) : (
        applications.map((application) => (
          <ApplicationCard
            key={application.id}
            application={application}
          />
        ))
      )}
    </>
  );
}

export default DashboardPage;