package com.cydeo.library.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json",
                "html:target/default-html-reports"},
        features = "src/test/resources/features",
        glue = "com/cydeo/library/step_definitions",
        tags = "@dashboard", //@WIP_DB-API @students @WIP @bookVerification@WIP_DB-API-UI
        dryRun = false
        )

public class CukesRunner {
}
