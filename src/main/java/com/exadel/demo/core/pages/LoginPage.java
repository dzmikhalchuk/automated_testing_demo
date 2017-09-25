package com.exadel.demo.core.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import static com.exadel.demo.core.utils.ElementsUtil.setInput;

public class LoginPage extends Page {

    public LoginPage(RemoteWebDriver driver) { super(driver); }

    @FindBy(css = "#ap_email")
    WebElement emailInput;

    @FindBy(css = "#ap_password")
    WebElement passwordInput;

    @FindBy(css = "#signInSubmit")
    WebElement signInButton;

    @FindBy(css = "#createAccountSubmit")
    WebElement createAccountButton;

    public LoginPage setEmail(String value) {
        logger.info("Set email: " + value);
        setInput(emailInput, value);
        return this;
    }

    public LoginPage setPassword(String value) {
        logger.info("Set password: " + value);
        setInput(passwordInput, value);
        return this;
    }

    public HomePage doClickSignIn() {
        logger.info("Click on sign in button");
        signInButton.click();
        return new HomePage(driver);
    }
}
