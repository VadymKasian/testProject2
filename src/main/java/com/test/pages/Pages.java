package com.test.pages;

public class Pages {
    private static FilteredSearchResultPage filteredSearchResultPage;
    private static SearchResultPage searchResultPage;
    private static MainPage mainPage;

    public static FilteredSearchResultPage filteredSearchResultPage() {
        if (filteredSearchResultPage == null)
            return new FilteredSearchResultPage();
        return filteredSearchResultPage;
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
