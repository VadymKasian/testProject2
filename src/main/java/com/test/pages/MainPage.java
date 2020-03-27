package com.test.pages;

import com.test.base.BasePage;
import com.test.locators.ID;
import com.test.locators.Locator;
import org.openqa.selenium.Keys;

public class MainPage extends BasePage {

    private Locator searchBox = new ID("twotabsearchtextbox");

    public void searchQuery(String text) {
        waitForElementToBeClickable(searchBox);
        type("Entering text in search box: " + text, text, searchBox);
    }

    public void aceptSearch(){
        pressEnter(searchBox);
    }
}
