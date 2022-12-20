package com.cydeo.library.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com/cydeo/library/step_definitions",
        tags = "@WIP",// @LoginUsingFile    @students @librarians
        dryRun = false
)


public class CukesRunner {
}
