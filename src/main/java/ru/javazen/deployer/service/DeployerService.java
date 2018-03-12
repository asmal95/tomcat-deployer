package ru.javazen.deployer.service;

import java.io.File;

public interface DeployerService {

    /**
     * Starts build and deploy for project.
     * @param repositoryUrl repository for build and deploy.
     * @return deployment process id.
     */
    Long processDeployment(String repositoryUrl);

}
