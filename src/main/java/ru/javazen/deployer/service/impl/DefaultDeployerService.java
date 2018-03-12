package ru.javazen.deployer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javazen.deployer.build.BuildService;
import ru.javazen.deployer.deploy.DeployService;
import ru.javazen.deployer.repository.RepositoryService;
import ru.javazen.deployer.service.DeployerService;

import java.io.File;

@Service
public class DefaultDeployerService implements DeployerService {

    private RepositoryService repositoryService;
    private BuildService buildService;
    private DeployService deployService;

    public DefaultDeployerService(
            @Autowired RepositoryService repositoryService,
            @Autowired BuildService buildService,
            @Autowired DeployService deployService) {
        this.repositoryService = repositoryService;
        this.buildService = buildService;
        this.deployService = deployService;
    }

    @Override
    public Long processDeployment(String repositoryUrl) {
        File repo = repositoryService.downloadRepository(repositoryUrl);
        File dist = buildService.build(repo);
        File artifact = null;
        if (dist.exists() && dist.isDirectory()) {
            for (File file : dist.listFiles()) {
                if (file.getName().endsWith(".war")) {
                    artifact = file;
                }
            }
        }
        String path = artifact.getName();
        path =  "/" + path.replace(".war", "");
        deployService.deployArtifact(artifact, path);

        return 1L;
    }

}
