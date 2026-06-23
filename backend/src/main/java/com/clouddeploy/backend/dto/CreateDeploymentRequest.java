package com.clouddeploy.backend.dto;

public class CreateDeploymentRequest {

    private String commitSha;
    private String branch;

    public String getCommitSha() {
        return commitSha;
    }

    public void setCommitSha(String commitSha) {
        this.commitSha = commitSha;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}