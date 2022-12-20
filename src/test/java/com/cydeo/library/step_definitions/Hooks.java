package com.cydeo.library.step_definitions;

import com.cydeo.library.utilities.Driver;
import io.cucumber.java.After;

public class Hooks {


    // be carefull io.cucumber.java.After; not org.junit !!! //
    @After
    public void tearDown(){
        Driver.closeDriver();
    }


}
