package com.test.pages;

import com.test.base.BasePage;
import com.test.locators.ClassName;
import com.test.locators.Locator;
import com.test.locators.XPath;

public class BookInformationPage extends BasePage {
    private static BookInformationPage bookInformationPage;

    private Locator bookAuthor = new XPath("//span[@class='author notFaded']");

    private Locator bookName = new ClassName("a-size-extra-large");

    private boolean bestSeller = false;

    public static BookInformationPage getInstance(){
        if (bookInformationPage == null){
            return new BookInformationPage();
        }
        return bookInformationPage;
    }

    private BookInformationPage(){
        if(getElementsCount(new ClassName("badge-wrapper")) > 0){
            bestSeller = true;
        }
    }

    public String getBookAuthor() {
        return getElement(bookAuthor).getText();
    }

    public String getBookName() {
        return getElement(bookName).getText();
    }

    public boolean isBestSeller() {
        return bestSeller;
    }

}
