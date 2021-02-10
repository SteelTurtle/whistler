package org.gorillacorp.whistler.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        stepNotifications = true,
        plugin = {"pretty", "html:target/cucumber"}
)
public class CucumberIntegrationTest {
}
