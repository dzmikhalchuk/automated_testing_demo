package com.exadel.demo.core.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultPage extends Page {

    public SearchResultPage(RemoteWebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#s-results-list-atf > li")
    List<WebElement> searchResultItems;

    public ProductPage clickOnProductById(int id) {
        searchResultItems.get(id).findElement(By.cssSelector("div.a-col-left")).click();
        return new ProductPage(driver);
    }
}
