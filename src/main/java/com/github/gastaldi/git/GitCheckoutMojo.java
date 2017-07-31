package com.github.gastaldi.git;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Clones only specific paths from a Git repository
 *
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 */
@Mojo(name = "git-checkout")
public class GitCheckoutMojo extends AbstractMojo {

    @Parameter(property = "outputDirectory", defaultValue = "${project.build.outputDirectory}")
    private File outputDirectory;

    @Parameter(property = "repository", required = true)
    private String repository;

    @Parameter(property = "branch", defaultValue = "master")
    private String branch;

    @Parameter(property = "paths", required = true)
    private List<String> paths;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            outputDirectory.mkdirs();
            if (Files.exists(outputDirectory.toPath().resolve(".git"))) {
                throw new MojoExecutionException("Cannot execute mojo in a directory that already contains a .git directory");
            }
            executeCommand(outputDirectory, "git", "init");
            executeCommand(outputDirectory, "git", "remote", "add", "origin", repository);
            executeCommand(outputDirectory, "git", "config", "core.sparseCheckout", "true");
            Path sparseCheckoutFile = outputDirectory.toPath().resolve(".git/info/sparse-checkout");
            Files.write(sparseCheckoutFile, paths);
            executeCommand(outputDirectory, "git", "pull", "origin", branch);
            executeCommand(outputDirectory, "rm", "-rf", ".git");
            getLog().info("Files were checked out in: " + outputDirectory);
        } catch (IOException e) {
            throw new MojoFailureException("Caught IOException in mojo", e);
        }
    }

    private final int executeCommand(File directory, String... command) throws IOException {
        Process process = new ProcessBuilder()
                .directory(directory)
                .command(command)
                .inheritIO()
                .start();
        try {
            return process.waitFor();
        } catch (InterruptedException e) {
            throw new IOException(e);
        }
    }
}
