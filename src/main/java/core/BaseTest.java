package core;

import org.junit.After;

import static core.DriverFactory.killDriver;

public class BaseTest {



    @After
    public void finalizeWebDriver() {
        killDriver();
    }



}
