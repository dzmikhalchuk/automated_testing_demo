package com.exadel.demo.core.pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Page {

    protected final RemoteWebDriver driver;
    protected final WebDriverWait wait;
    protected final Logger logger;

    public Page(RemoteWebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 60);
        this.logger = LoggerFactory.getLogger(this.getClass());
        driver.manage().timeouts().implicitlyWait(3, SECONDS);
        PageFactory.initElements(driver, this);
    }

    public HomePage navigateToHomePage(String url){
        logger.info("Navigate to Home page");
        driver.get(url);
        return new HomePage(driver);
    }

}