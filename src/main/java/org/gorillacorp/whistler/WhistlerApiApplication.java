package org.gorillacorp.whistler;

import lombok.val;
import org.gorillacorp.whistler.configuration.JpaPersistenceContextConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WhistlerApiApplication {

    private static final Class<?>[] configurationClasses = {
            JpaPersistenceContextConfiguration.class,
            WhistlerApiApplication.class
    };

    public static void main(String[] args) {
        val springApp = new SpringApplication(configurationClasses);
        springApp.run(args);
    }

}
