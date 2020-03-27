package com.test.pages;

import com.test.base.BasePage;
import com.test.locators.ID;
import com.test.locators.Locator;

public class MainPage extends BasePage {
    private static MainPage mainPage;

    private Locator searchBox = new ID("twotabsearchtextbox");

    public static MainPage getInstance() {
        if (mainPage == null) {
            return new MainPage();
        }
        return mainPage;
    }

    private MainPage() {
    }

    public void searchQuery(String text) {
        waitForElementToBeClickable(searchBox);
        type("Entering text in search box: " + text, text, searchBox);
        wait(1);
        pressEnter(searchBox);
    }
}
