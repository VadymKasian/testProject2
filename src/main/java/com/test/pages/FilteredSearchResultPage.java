package com.test.pages;

import com.test.base.BasePage;
import com.test.locators.Locator;
import com.test.locators.XPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FilteredSearchResultPage extends BasePage {

    private Locator searchResult = new XPath("//div[@class='a-section a-spacing-medium']");
    private Locator bookName = new XPath(".//div[@class='sg-col-inner']/div/h2");
    private Locator bookAuthor = new XPath(".//div[@class='sg-col-inner']/div/div[@class='a-row a-size-base a-color-secondary']");

    public List<WebElement> getSearchResult() {
        return getElements(searchResult);
    }

    public String getBookTitle(WebElement element) {
        return getElementText("Getting bookName value", element, bookName);
    }

    public String getBookAuthor(WebElement element) {
        String author = getElementText("Getting bookAuthor value", element, bookAuthor);
        if (author.contains(" | ")) {
            author = author.substring(0, author.indexOf(" | ") - 1);
        }
        return author.substring(3, author.length());
    }
}
