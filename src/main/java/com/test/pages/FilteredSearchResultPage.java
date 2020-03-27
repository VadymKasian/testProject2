package com.test.pages;

import com.test.base.BasePage;
import com.test.locators.Locator;
import com.test.locators.XPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FilteredSearchResultPage extends BasePage {
    private static FilteredSearchResultPage filteredSearchResultPage;

    private List<WebElement> searchResult = getElements(new XPath("//div[@class='a-section a-spacing-medium']"));
    private Locator bookName = new XPath(".//h2/a/span");
    private Locator bookAuthor = new XPath(".//div[@class='a-section a-spacing-none']/div[@class='a-row a-size-base a-color-secondary']");

    public static FilteredSearchResultPage getInstance() {
        if (filteredSearchResultPage == null) {
            return new FilteredSearchResultPage();
        }
        return filteredSearchResultPage;
    }

    private FilteredSearchResultPage() {
    }

    public List<WebElement> getSearchResult() {
        return searchResult;
    }

    public boolean isBestseller(WebElement element) {
        if (element.findElement(By.xpath(".//div[@class='a-section a-spacing-micro s-min-height-small']")).getText().length() > 1)
            return true;
        return false;
    }

    public String getBookName(WebElement element) {
        return element.findElement(By.xpath(".//div[@class='sg-col-inner']/div/h2")).getText();
    }

    public String getBookAuthor(WebElement element) {
        String author = element.findElement(By
                .xpath(".//div[@class='sg-col-inner']/div/div[@class='a-row a-size-base a-color-secondary']"))
                .getText();
        if (author.contains(" | ")) {
            author = author.substring(0, author.indexOf(" | ") - 1);
        }
        author = author.substring(3, author.length());
        return author;
    }
}
