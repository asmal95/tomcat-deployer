package ru.javazen.deployer.deploy;

import java.io.File;

public interface DeployService {

    /**
     * Deploy artifact on the web-server
     * @param artifact path to artifact for deploy.
     * @return deployment status
     */
    boolean deployArtifact(File artifact, String path);
}
