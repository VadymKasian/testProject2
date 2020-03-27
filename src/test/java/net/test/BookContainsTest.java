package net.test;

import com.test.base.BaseTest;
import com.test.pages.BookInformationPage;
import com.test.pages.FilteredSearchResultPage;
import com.test.pages.MainPage;
import com.test.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class BookContainsTest extends BaseTest {

    /*
    @  0 - bestseller ("true" \ "false")
    @  1 - name
    @  2 - author
    */
    List<String> bookInformation = new ArrayList<String>();

    List<List<String>> library = new ArrayList<List<String>>();

    @Test
    public void testAddBooksInCollection() throws InterruptedException {
        MainPage.getInstance().searchQuery("Java");

        SearchResultPage.getInstance().clickBooksFilter();

        Thread.sleep(500);
        FilteredSearchResultPage.getInstance().getSearchResult().forEach(element -> {
            bookInformation = new ArrayList<String>();

            // check bestseller
            bookInformation.add(String.valueOf(FilteredSearchResultPage.getInstance().isBestseller(element)));

            // name
            bookInformation.add(FilteredSearchResultPage.getInstance().getBookName(element));

            // author(s)
            bookInformation.add(FilteredSearchResultPage.getInstance().getBookAuthor(element));

            library.add(bookInformation);
        });

        System.out.println("Book list");
        for (List<String> book: library){
            System.out.println(book.toString());
        }

        driver.get("https://www.amazon.com/Head-First-Java-Kathy-Sierra/dp/0596009208/ref=sr_1_3");

        String name = BookInformationPage.getInstance().getBookName();
        boolean contains = false;

        //check book from URL contains in collection
        for (List<String> list: library) {
            if(name.equals(list.get(1))){
                contains = true;
                break;
            }

        }
        Assert.assertTrue(contains);
    }
}
