package ru.javazen.deployer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.javazen.deployer.service.DeployerService;

@RestController
@RequestMapping("/deployer")
public class DeployerController {

    private DeployerService deployerService;

    public DeployerController(@Autowired DeployerService deployerService) {
        this.deployerService = deployerService;
    }

    @GetMapping("/deploy")
    public String deploy(@RequestParam String repositoryUrl) {
        System.out.println(repositoryUrl);
        return deployerService.processDeployment(repositoryUrl).toString();
    }
}
