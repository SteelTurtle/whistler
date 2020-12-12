package org.gorillacorp.whistler;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseInjector implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        //todo: prepopulate the DB at startup with test data
    }
}
