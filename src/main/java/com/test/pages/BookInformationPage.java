package com.test.pages;

import com.test.base.BasePage;
import com.test.locators.ClassName;
import com.test.locators.Locator;
import com.test.locators.XPath;

public class BookInformationPage extends BasePage {

    private Locator bookAuthor = new XPath("//span[@class='author notFaded']");
    private Locator bookName = new ClassName("a-size-extra-large");

    public String getBookAuthor() {
        String author = getElement(bookAuthor).getText();
        if (author.contains(" (Author)")) {
            author = author.substring(0, author.indexOf(" (Author)"));
        }
        return author;//.substring(3, author.length());
    }

    public String getBookName() {
        return getElement(bookName).getText();
    }


}
