package com.exadel.demo.core.utils;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public RemoteWebDriver getDriver(String browser) throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), getBrowserCapabilities(browser));
    }

    private DesiredCapabilities getBrowserCapabilities(String browserType) {
        switch (browserType) {
            case "firefox":
                logger.info("Opening firefox driver");
                return DesiredCapabilities.firefox();
            case "chrome":
                logger.info("Opening chrome driver");
                return DesiredCapabilities.chrome();
            case "IE":
                logger.info("Opening IE driver");
            return DesiredCapabilities.internetExplorer();
            default:
                logger.info("browser : " + browserType + " is invalid, Launching Chrome as browser of choice..");
                return DesiredCapabilities.chrome();
        }
    }
}
