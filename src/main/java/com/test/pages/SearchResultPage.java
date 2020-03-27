package com.test.pages;

import com.test.base.BasePage;
import com.test.locators.Locator;
import com.test.locators.XPath;

public class SearchResultPage extends BasePage {
    private static SearchResultPage searchResultPage;

    private Locator booksFilter =
            new XPath("//span[contains(@class,'a-size-base a-color-base') and contains(text(),'Books')]");

    public static SearchResultPage getInstance() {
        if (searchResultPage == null) {
            return new SearchResultPage();
        }
        return searchResultPage;
    }

    private SearchResultPage() {
    }

    public void clickBooksFilter() {
        waitForElementToBeClickable(booksFilter);
        click("Clicking books filter", booksFilter);
    }
}
