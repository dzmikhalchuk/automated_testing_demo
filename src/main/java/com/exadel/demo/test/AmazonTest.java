package com.exadel.demo.test;

import com.exadel.demo.core.pages.HomePage;
import com.exadel.demo.core.pages.Page;
import com.exadel.demo.core.pages.ProductPage;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.*;

import static org.testng.Assert.assertTrue;
import static ru.yandex.qatools.allure.model.SeverityLevel.CRITICAL;
import static ru.yandex.qatools.allure.model.SeverityLevel.MINOR;

public class AmazonTest extends TestBase {

    @Features("PRODUCTS")
    @Stories("PRODUCT ELEMENTS")
    @Title("Test product elements")
    @Severity(MINOR)
    @Description("Verify that product page contains all elements")
    @Test
    public void testProductElements() {
        Page page = new Page(driver);
        HomePage homePage = page.navigateToHomePage(propertiesLoader.getBasePage());
        ProductPage productPage = homePage.setSearchCategory("Amazon Devices")
                .inputProductName("Kindle")
                .clickOnSearchButton()
                .clickOnProductById(0);
        SoftAssert assertion = new SoftAssert();
        assertTrue(productPage.isProductTitleDisplayed());
        assertTrue(productPage.isProductRateDisplayed());
        assertTrue(productPage.isProductPriceDisplayed());
        assertTrue(productPage.isProductImageDisplayed());
        assertion.assertAll();
    }

    @Features("PRODUCTS")
    @Stories("PRODUCT SEARCH")
    @Title("Test product title: Phone")
    @Severity(MINOR)
    @Description("Verify that title contains product name")
    @Test
    public void testProductTitleIphone() {
        Page page = new Page(driver);
        HomePage homePage = page.navigateToHomePage(propertiesLoader.getBasePage());
        ProductPage productPage = homePage.setSearchCategory("Amazon Devices")
                .inputProductName("Kindle")
                .clickOnSearchButton()
                .clickOnProductById(0);
        assertTrue(StringUtils.containsIgnoreCase(productPage.getProductTitleText(), "Phone"), "Product title does not contain 'Iphone'");
    }

    @Features("PRODUCTS")
    @Stories("PRODUCT SEARCH")
    @Severity(CRITICAL)
    @Description("Verify that title contains product name")
    @Test
    public void testProductTitleKindle() {
        Page page = new Page(driver);
        HomePage homePage = page.navigateToHomePage(propertiesLoader.getBasePage());
        ProductPage productPage = homePage.setSearchCategory("Amazon Devices")
                .inputProductName("Kindle")
                .clickOnSearchButton()
                .clickOnProductById(0);
        assertTrue(StringUtils.containsIgnoreCase(productPage.getProductTitleText(), "Kindle"), "Product title does not contain 'Kindle'");
    }
}
