package com.clouddeploy.backend.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;

@Service
public class DockerBuildService {

    public void buildImage(Path workspace, String imageTag)
            throws IOException, InterruptedException {

        ProcessBuilder processBuilder = new ProcessBuilder(
                "docker",
                "build",
                "-t",
                imageTag,
                workspace.toString());

        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        String output =
                new String(process.getInputStream().readAllBytes());

        int exitCode = process.waitFor();

        System.out.println("========== DOCKER BUILD ==========");
        System.out.println("Image Tag : " + imageTag);
        System.out.println("Workspace : " + workspace);
        System.out.println(output);
        System.out.println("Exit Code : " + exitCode);
        System.out.println("==================================");

        if (exitCode != 0) {
            throw new RuntimeException(
                    "Docker build failed:\n" + output);
        }
    }
}