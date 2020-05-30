package net.test;

import com.test.base.BaseTest;
import com.test.entities.Book;
import com.test.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class BookContainsTest extends BaseTest {

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

        book = new Book();
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");
        boolean contains = false;

        //check book from URL contains in collection
        for (Book libraryBook : library) {
            if(book.equals(libraryBook)){
                contains = true;
                break;
            }
        }
        Assert.assertTrue(contains);
    }
}
