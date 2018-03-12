package ru.javazen.deployer.repository;

import java.io.File;

public interface RepositoryService {

    /**
     * Downloaded project files from repository.
     * @param repositoryUrl repository URL.
     * @return path to downloaded repository files
     */
    File downloadRepository(String repositoryUrl);
}
