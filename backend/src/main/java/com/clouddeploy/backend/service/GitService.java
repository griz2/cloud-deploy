package com.clouddeploy.backend.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class GitService {

    public Path cloneRepository(String repositoryUrl)
            throws IOException, InterruptedException {

        Path workspace =
                Files.createTempDirectory(
                        "deployment-" + UUID.randomUUID());

        ProcessBuilder processBuilder =
                new ProcessBuilder(
                        "git",
                        "clone",
                        repositoryUrl,
                        workspace.toString());

        Process process = processBuilder.start();

        int exitCode = process.waitFor();

        if (exitCode != 0) {

            throw new RuntimeException(
                    "Failed to clone repository.");

        }

        return workspace;
    }
}