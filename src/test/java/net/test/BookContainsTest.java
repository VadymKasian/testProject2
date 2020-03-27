package net.test;

import com.test.base.BaseTest;
import com.test.entities.Book;
import com.test.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class BookContainsTest extends BaseTest {

    /*
    @  1 - name
    @  2 - author
    */
    List<Book> library = new ArrayList<Book>();
    Book book;

    @Test
    public void testAddBooksInCollection() throws InterruptedException {
        Pages.mainPage().searchQuery("Java");
        Pages.mainPage().aceptSearch();

        Pages.searchResultPage().clickBooksFilter();

        Thread.sleep(500);
        Pages.filteredSearchResultPage().getSearchResult().forEach(element -> {
            book = new Book();

            // title
            book.setTitle(Pages.filteredSearchResultPage().getBookTitle(element));

            // author(s)
            book.setAuthor(Pages.filteredSearchResultPage().getBookAuthor(element));

            library.add(book);
        });

        driver.get("https://www.amazon.com/Head-First-Java-Kathy-Sierra/dp/0596009208/ref=sr_1_3");

        book = new Book();
        book.setTitle(Pages.bookInformationPage().getBookName());
        book.setAuthor(Pages.bookInformationPage().getBookAuthor());
        boolean contains = false;

        //check book from URL contains in collection
        for (Book libraryBook : library) {
            if(book.equals(libraryBook)){
                contains = true;
            }
        }
        Assert.assertTrue(contains);
    }
}
