package com.test.pages;

import com.test.base.BasePage;
import com.test.locators.Locator;
import com.test.locators.XPath;

public class SearchResultPage extends BasePage {

    private Locator booksFilter =
            new XPath("//span[contains(@class,'a-size-base a-color-base') and contains(text(),'Books')]");

    public void clickBooksFilter() {
        waitForElementToBeClickable(booksFilter);
        click("Clicking books filter", booksFilter);
    }
}
