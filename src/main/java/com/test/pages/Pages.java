package com.test.pages;

public class Pages {
    private static FilteredSearchResultPage filteredSearchResultPage;
    private static BookInformationPage bookInformationPage;
    private static SearchResultPage searchResultPage;
    private static MainPage mainPage;

    public static FilteredSearchResultPage filteredSearchResultPage() {
        if (filteredSearchResultPage == null)
            return new FilteredSearchResultPage();
        return filteredSearchResultPage;
    }

    public static BookInformationPage bookInformationPage() {
        if (bookInformationPage == null)
            return new BookInformationPage();
        return bookInformationPage;
    }

    public static SearchResultPage searchResultPage() {
        if (searchResultPage == null)
            return new SearchResultPage();
        return searchResultPage;
    }

    public static MainPage mainPage() {
        if (mainPage == null)
            return new MainPage();
        return mainPage;
    }
}
