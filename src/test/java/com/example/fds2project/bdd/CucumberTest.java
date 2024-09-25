package com.example.fds2project.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
//import io.cucumber.junit.platform.engine.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty"},
        glue = {"com.example.fds2project.bdd"}
)
public class CucumberTest {
}
