package ru.javazen.deployer.repository.impl;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.javazen.deployer.repository.RepositoryService;

import java.io.File;

@Service
public class GitHubRepositoryService implements RepositoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubRepositoryService.class);
    private static final String GIT_SUFFIX = ".git";
    private static final String PATH_DELIMITER = "/";

    @Override
    public File downloadRepository(String repositoryUrl) {

        File projectDirectory = new File(System.getProperty("user.home")
                + "/" + extractProjectName(repositoryUrl));

        if (projectDirectory.exists()) {
            delete(projectDirectory);
        }
        if (!projectDirectory.mkdirs()) {
            throw new RuntimeException("Can't create directory: " + projectDirectory);
        }


        LOGGER.debug("Cloning from {} to {}", repositoryUrl, projectDirectory);

        try (Git result = Git.cloneRepository()
                .setURI(repositoryUrl)
                .setDirectory(projectDirectory)
                .call()) {

            LOGGER.debug("Having repository: {}", result.getRepository().getDirectory().getPath());
            return result.getRepository().getDirectory().getParentFile();
        } catch (GitAPIException e) {
            LOGGER.error("Git clone fails", e);
            throw new RuntimeException("Git clone fails", e);
        }
    }

    private String extractProjectName(String projectUrl) {
        int last = projectUrl.lastIndexOf(PATH_DELIMITER);
        int git = projectUrl.indexOf(GIT_SUFFIX);
        return projectUrl.substring(last + 1, git);
    }

    private void delete(File f) {
        if (f.isDirectory()) {
            for (File c : f.listFiles()) {
                delete(c);
            }
        }
        if (!f.delete()) {
            throw new RuntimeException("Failed to delete file: " + f);
        }
    }
}
