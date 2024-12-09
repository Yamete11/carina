package com.zebrunner.carina.nhl;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public abstract class SearchPageBase extends AbstractPage {

    @FindBy(xpath = "//h2[@class='nhl-o-pattern-title__title nhl-ty-heading--h3 -center']")
    private ExtendedWebElement pageTitle;

    @FindBy(xpath = "//input[@id='input-search-query']")
    private ExtendedWebElement searchQuery;

    @FindBy(xpath = "//button[@id='search__btn']")
    private ExtendedWebElement searchButton;

    @FindBy(xpath = "//p[@class='nhl-ty-subtitle--3 -text-center']")
    private ExtendedWebElement searchSubTitle;

    public SearchPageBase(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(pageTitle);
    }

    @Override
    public boolean isPageOpened() {
        return pageTitle.isElementPresent();
    }

    public void enterQuery(String text){
        searchQuery.type(text);
        searchButton.click();
    }

    public ExtendedWebElement getSearchSubTitle() {
        return searchSubTitle;
    }
}
