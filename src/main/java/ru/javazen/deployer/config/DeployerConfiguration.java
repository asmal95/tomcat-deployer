package ru.javazen.deployer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.javazen.deployer.deploy.tomcat.TomcatManager;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class DeployerConfiguration {

    @Bean
    public TomcatManager tomcatManager(
            @Value("${tomcat.url}") String tomcatUrl,
            @Value("${tomcat.username}") String username,
            @Value("${tomcat.password}") String password) throws MalformedURLException {
        URL url = new URL(tomcatUrl);
        TomcatManager tomcatManager = new TomcatManager(url, username, password);
        return tomcatManager;
    }
}
