import { useState } from "react";
import { createApplication } from "../../services/applicationService";

type NewApplicationModalProps = {
  open: boolean;
  onClose: () => void;
  onCreated: () => Promise<void>;
};

function NewApplicationModal({
  open,
  onClose,
  onCreated,
}: NewApplicationModalProps) {
  const [name, setName] = useState("");
  const [repositoryUrl, setRepositoryUrl] = useState("");
  const [dockerfilePath, setDockerfilePath] = useState("Dockerfile");
  const [environment, setEnvironment] = useState("");
  const [healthCheckUrl, setHealthCheckUrl] = useState("");

  async function handleCreate() {
    try {
      await createApplication({
        name,
        repositoryUrl,
        dockerfilePath,
        environment,
        healthCheckUrl,
      });

      await onCreated();

      setName("");
      setRepositoryUrl("");
      setDockerfilePath("Dockerfile");
      setEnvironment("");
      setHealthCheckUrl("");

      onClose();
    } catch (error) {
      console.error(error);
      alert("Failed to create application.");
    }
  }

  if (!open) {
    return null;
  }

  return (
    <div
      style={{
        position: "fixed",
        inset: 0,
        backgroundColor: "rgba(0,0,0,0.5)",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <div
        style={{
          backgroundColor: "white",
          padding: "2rem",
          borderRadius: "8px",
          minWidth: "450px",
        }}
      >
        <h2>New Application</h2>

        <div style={{ display: "flex", flexDirection: "column", gap: "1rem" }}>
          <input
            type="text"
            placeholder="Application Name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />

          <input
            type="text"
            placeholder="Repository URL"
            value={repositoryUrl}
            onChange={(e) => setRepositoryUrl(e.target.value)}
          />

          <input
            type="text"
            placeholder="Dockerfile Path"
            value={dockerfilePath}
            onChange={(e) => setDockerfilePath(e.target.value)}
          />

          <select
            value={environment}
            onChange={(e) => setEnvironment(e.target.value)}
          >
            <option value="">Select Environment</option>
            <option value="DEVELOPMENT">Development</option>
            <option value="STAGING">Staging</option>
            <option value="PRODUCTION">Production</option>
          </select>

          <input
            type="text"
            placeholder="Health Check URL"
            value={healthCheckUrl}
            onChange={(e) => setHealthCheckUrl(e.target.value)}
          />
        </div>

        <div
          style={{
            marginTop: "1.5rem",
            display: "flex",
            gap: "1rem",
          }}
        >
          <button onClick={handleCreate}>Create</button>

          <button onClick={onClose}>Cancel</button>
        </div>
      </div>
    </div>
  );
}

export default NewApplicationModal;