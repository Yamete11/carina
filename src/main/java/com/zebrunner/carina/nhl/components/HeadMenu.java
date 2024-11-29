package com.zebrunner.carina.nhl.components;

import com.zebrunner.carina.nhl.NewsPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HeadMenu extends AbstractUIObject {

    @FindBy(xpath = "./div/nav[1]/ul/li[1]/a")
    private ExtendedWebElement newsButton;


    public HeadMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public NewsPage openNewsPage(){
        newsButton.hover();
        newsButton.click();
        return new NewsPage(driver);
    }
}