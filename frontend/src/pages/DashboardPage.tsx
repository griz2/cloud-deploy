import ApplicationCard from "../components/dashboard/ApplicationCard";
import { useApplications } from "../hooks/useApplications";

function DashboardPage() {
  const { applications, loading } = useApplications();

  if (loading) {
    return <p>Loading applications...</p>;
  }

  return (
    <>
      <h1>Dashboard</h1>

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