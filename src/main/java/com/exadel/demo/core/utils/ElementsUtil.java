package com.exadel.demo.core.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementsUtil {

    private static final Logger log = LoggerFactory.getLogger(ElementsUtil.class);
    private static long defaultTimeout = 15;


    public static void waitForPageLoaded(WebDriver driver) {
        ExpectedCondition<Boolean> expectation = driver1 -> ((JavascriptExecutor) driver1).executeScript("return document.readyState").equals("complete");
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        try {
            wait.until(expectation);
        } catch (Exception error) {
            log.error("Timeout waiting for Page Load Request to complete.", true);
        }
    }

    public static void waitForElementGetsVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, defaultTimeout).until(ExpectedConditions.visibilityOf(element));
    }

    public static boolean isElementDisplayedAndEnabled(WebElement element) {
        return (element.isDisplayed() && element.isEnabled());
    }

    public static void setInput(WebElement inputElement, String value) {
        inputElement.clear();
        inputElement.sendKeys(value);
    }
}