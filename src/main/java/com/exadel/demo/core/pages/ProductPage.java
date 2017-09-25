package com.exadel.demo.core.pages;

import com.exadel.demo.core.utils.ElementsUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

public class ProductPage extends Page {

    public ProductPage(RemoteWebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#productTitle")
    WebElement productTitle;

    @FindBy(css = "#averageCustomerReviews")
    WebElement productRate;

    @FindBy(css = "#price")
    WebElement productPrice;

    @FindBy(css = "#imgTagWrapperId")
    WebElement productImage;

    @Step("Verify that is product title presented")
    public boolean isProductTitleDisplayed() {
        return ElementsUtil.isElementDisplayedAndEnabled(productTitle);
    }

    @Step("Verify that is product rate presented")
    public boolean isProductRateDisplayed() {
        return ElementsUtil.isElementDisplayedAndEnabled(productRate);
    }

    @Step("Verify that is product price presented")
    public boolean isProductPriceDisplayed() {
        return ElementsUtil.isElementDisplayedAndEnabled(productPrice);
    }

    @Step("Verify that is product image presented")
    public boolean isProductImageDisplayed() {
        return ElementsUtil.isElementDisplayedAndEnabled(productImage);
    }

    @Step("Getting product title text")
    public String getProductTitleText() {
        return productTitle.getText();
    }
}
