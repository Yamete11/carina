package com.zebrunner.carina.nhl.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public enum HeadLinks {
    NEWS("//a[@href='/news']"),
    PLAYERS("//a[@href='/player']"),
    LOGIN("//a[@class='nhl-o-menu__link' and @title='Sign In']"),
    PROFILE("//a[@class='nhl-o-menu__link' and @title='Profile']"),
    SEARCH("//a[@class='nhl-c-header__btn nhl-c-header__btn--search' and @title='Search']");

    private final String xpath;

    HeadLinks(String xpath) {
        this.xpath = xpath;
    }

    public String getXpath() {
        return xpath;
    }
}
