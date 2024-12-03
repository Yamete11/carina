package com.zebrunner.carina.nhl.components;

public enum HeadLinks {
    NEWS("News", "https://www.nhl.com/news/"),
    PLAYERS("Players", "https://www.nhl.com/player"),
    LOGIN("Sign In", "https://account.nhl.com/ui/?client_id=a2nhl&lang=en&redirect_uri=https%3A%2F%2Faccount.nhl.com%2Fui%2F&returnUrl=https%3A%2F%2Fwww.nhl.com%2F"),
    PROFILE("Profile", "https://account.nhl.com/ui/"),
    SEARCH("Search", "https://www.nhl.com/search/");

    private final String title;
    private final String url;

    HeadLinks(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
