package com.clouddeploy.backend.service;

import org.springframework.stereotype.Service;

@Service
public class DockerService {
    public void verifyDockerInstalled() {

        try {

            ProcessBuilder processBuilder =
                    new ProcessBuilder("docker", "--version");

            Process process = processBuilder.start();

            int exitCode = process.waitFor();

            if (exitCode != 0) {

                throw new RuntimeException("Docker is not installed.");

            }

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }
}