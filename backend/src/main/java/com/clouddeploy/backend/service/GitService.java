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

        System.out.println("========== GIT CLONE ==========");
        System.out.println("Repository: " + repositoryUrl);
        System.out.println("Workspace : " + workspace);

        ProcessBuilder processBuilder =
                new ProcessBuilder(
                        "git",
                        "clone",
                        repositoryUrl,
                        workspace.toString());

        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        String output = new String(
                process.getInputStream().readAllBytes());

        int exitCode = process.waitFor();

        System.out.println(output);
        System.out.println("Exit Code: " + exitCode);

        if (exitCode != 0) {
            throw new RuntimeException(
                    "Git clone failed:\n" + output);
        }

        System.out.println("Workspace exists: " + Files.exists(workspace));

        System.out.println("===============================");

        return workspace;
    }
}