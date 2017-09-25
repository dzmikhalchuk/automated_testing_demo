package com.exadel.demo.core.pages;

import com.exadel.demo.core.utils.ElementsUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.allure.annotations.Step;

import static com.exadel.demo.core.utils.ElementsUtil.setInput;
import static com.exadel.demo.core.utils.ElementsUtil.waitForPageLoaded;

public class HomePage extends Page
{
    public HomePage(RemoteWebDriver driver) { super(driver); }

    @FindBy(css = "#nav-tools")
    WebElement navigation;

    @FindBy(css = "#nav-link-accountList")
    WebElement accountList;

    @FindBy(css = "#searchDropdownBox")
    WebElement categoryDropdown;

    @FindBy(css = "#twotabsearchtextbox")
    WebElement searchInput;

    @FindBy(xpath = "//*[@id='nav-search-submit-text']/ancestor::div[1]")
    WebElement searchButton;

    public boolean isNavigationPresented() {
        return ElementsUtil.isElementDisplayedAndEnabled(navigation);
    }

    @Step("Navigate to login page")
    public LoginPage navigateToLoginPage() {
        logger.info("Navigate to login page");
        accountList.click();
        waitForPageLoaded(driver);
        return new LoginPage(driver);
    }

    @Step("Set search category")
    public HomePage setSearchCategory(String categoryName) {
        logger.info("Set search category");
        Select categories = new Select(categoryDropdown);
        categories.selectByVisibleText(categoryName);
        return this;
    }

    @Step("Input product name")
    public HomePage inputProductName(String productName) {
        setInput(searchInput, productName);
        return this;
    }

    @Step("Click on search button")
    public SearchResultPage clickOnSearchButton() {
        logger.info("Click on search button");
        searchButton.click();
        return new SearchResultPage(driver);
    }
}
