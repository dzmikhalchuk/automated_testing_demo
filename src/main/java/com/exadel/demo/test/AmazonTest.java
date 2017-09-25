package com.exadel.demo.test;

import com.exadel.demo.core.pages.HomePage;
import com.exadel.demo.core.pages.Page;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import static com.exadel.demo.core.enums.TestGroups.SMOKE;
import static org.testng.Assert.assertTrue;

public class AmazonTest extends TestBase {

    @Title("SignIn")
    @Test(groups = {SMOKE})
    public void testSignIn() {
      Page page = new Page(driver);
        HomePage homePage = page.navigateToHomePage(propertiesLoader.getBasePage())
                .navigateToLoginPage()
                .setEmail(propertiesLoader.getUserEmail())
                .setPassword(propertiesLoader.getUserPassword())
                .doClickSignIn();
        assertTrue(homePage.isNavigationPresented());
    }

    @Title("Product")
    @Test(groups = {SMOKE})
    public void testProductSearch() {
        Page page = new Page(driver);
        HomePage homePage = page.navigateToHomePage(propertiesLoader.getBasePage())
                .navigateToLoginPage()
                .setEmail(propertiesLoader.getUserEmail())
                .setPassword(propertiesLoader.getUserPassword())
                .doClickSignIn();
        homePage.setSearchCategory("Amazon Devices")
                .inputProductName("Kindle")
                .clickOnSearchButton()
                .clickOnProductById(0);
    }
}
