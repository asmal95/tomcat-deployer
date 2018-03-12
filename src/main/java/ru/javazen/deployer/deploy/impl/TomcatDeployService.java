package ru.javazen.deployer.deploy.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javazen.deployer.deploy.DeployService;
import ru.javazen.deployer.deploy.tomcat.TomcatManager;

import java.io.File;
import java.io.IOException;

@Service
public class TomcatDeployService implements DeployService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TomcatDeployService.class);
    public TomcatDeployService(@Autowired TomcatManager tomcatManager) {
        this.tomcatManager = tomcatManager;
    }

    private TomcatManager tomcatManager;

    @Override
    public boolean deployArtifact(File artifact, String path) {

        try {
            tomcatManager.deploy(path, artifact, true);
        } catch (IOException e) {
            LOGGER.error("Can't deploy artifact {} to path {}", artifact.getPath(), path, e);
            throw new RuntimeException("Can't deploy artifact", e);
        }

        return true;
    }
}
