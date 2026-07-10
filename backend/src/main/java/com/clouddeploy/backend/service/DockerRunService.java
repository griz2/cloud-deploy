package com.clouddeploy.backend.service;

import org.springframework.stereotype.Service;
import com.clouddeploy.backend.model.ApplicationEnvironmentVariable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DockerRunService {

        public String runContainer(
                String imageTag,
                List<ApplicationEnvironmentVariable> environmentVariables)
                        throws IOException, InterruptedException {

                List<String> command = new ArrayList<>();

                command.add("docker");
                command.add("run");
                command.add("-d");

                for (ApplicationEnvironmentVariable variable : environmentVariables) {

                command.add("-e");
                command.add(
                        variable.getVariableKey()
                                + "="
                                + variable.getVariableValue());

                }

                command.add(imageTag);

                ProcessBuilder processBuilder =
                        new ProcessBuilder(command);

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