package com.exadel.demo.test;

import com.exadel.demo.core.utils.DriverFactory;
import com.exadel.demo.core.utils.PropertiesLoader;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;

public class TestBase {

    protected RemoteWebDriver driver;
    public final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    protected PropertiesLoader propertiesLoader = new PropertiesLoader();


    @BeforeMethod
    @Parameters({"browser"})
    public void init(@Optional String browser) throws MalformedURLException {
        logger.info("Driver initialisation");
        driver = new DriverFactory().getDriver(browser);
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void cleanup() {
        logger.info("Close driver");
        driver.close();
    }
}
