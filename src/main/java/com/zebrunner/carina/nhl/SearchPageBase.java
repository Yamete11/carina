package com.zebrunner.carina.nhl;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class SearchPageBase extends AbstractPage {
    public SearchPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void enterQuery(String text);

    public abstract ExtendedWebElement getSearchSubTitle();
}
