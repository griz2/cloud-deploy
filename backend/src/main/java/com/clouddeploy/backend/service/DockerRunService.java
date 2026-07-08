package com.clouddeploy.backend.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DockerRunService {

        public String runContainer(String imageTag)
                throws IOException, InterruptedException {

                ProcessBuilder processBuilder = new ProcessBuilder(
                        "docker",
                        "run",
                        "-d",
                        imageTag);

                processBuilder.redirectErrorStream(true);

                Process process = processBuilder.start();

                String output =
                        new String(process.getInputStream().readAllBytes()).trim();

                int exitCode = process.waitFor();

                System.out.println("========== DOCKER RUN ==========");
                System.out.println("Image Tag : " + imageTag);
                System.out.println("Container : " + output);
                System.out.println("Exit Code : " + exitCode);
                System.out.println("================================");

                if (exitCode != 0) {
                        throw new RuntimeException(
                                "Docker run failed:\n" + output);
                }

                return output;
        }

        public boolean isContainerRunning(String containerId)
                throws IOException, InterruptedException {

                ProcessBuilder processBuilder = new ProcessBuilder(
                        "docker",
                        "inspect",
                        "-f",
                        "{{.State.Running}}",
                        containerId);

                processBuilder.redirectErrorStream(true);

                Process process = processBuilder.start();

                String output =
                        new String(process.getInputStream().readAllBytes()).trim();

                int exitCode = process.waitFor();

                if (exitCode != 0) {
                        return false;
                }

                return output.equals("true");
        }
}