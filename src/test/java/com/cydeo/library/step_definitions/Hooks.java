
package com.cydeo.library.step_definitions;

import com.cydeo.library.utilities.DBUtils;
import com.cydeo.library.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;


public class Hooks {

	@Before("@db")
	public void dbHook() {
		System.out.println("\ncreating database connection !!! 111 DB 111 !!!\n");
		DBUtils.createConnection();
	}
	
	@After("@db")
	public void afterDbHook() {
		System.out.println("\nclosing database connection  !!! 000 DB 000 !!!\n");
		DBUtils.destroyConnection();

	}
	
	@Before ("@ui")
	public void setUp() {
		// we put a logic that should apply to every scenario
		Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	@After
	public void tearDown(Scenario scenario) {
		// only takes a screenshot if the scenario fails
		if (scenario.isFailed()) {
			// taking a screenshot
			final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot,"image/png", "screenshot");

		}
		Driver.closeDriver();
	}


}
