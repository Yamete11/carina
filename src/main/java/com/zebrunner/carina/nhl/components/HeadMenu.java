package com.zebrunner.carina.nhl.components;

import com.zebrunner.carina.nhl.NewsPage;
import com.zebrunner.carina.nhl.NewsPageBase;
import com.zebrunner.carina.nhl.PlayersPage;
import com.zebrunner.carina.nhl.PlayersPageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HeadMenu extends AbstractUIObject {

    @FindBy(xpath = "./div/nav[1]/ul/li[1]/a")
    private ExtendedWebElement newsButton;

    @FindBy(xpath = "//*[@id=\"hamburger-menu\"]/ul[1]/li[4]/a")
    private ExtendedWebElement playersButton;


    public HeadMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public NewsPageBase openNewsPage(){
        newsButton.hover();
        newsButton.click();
        return initPage(driver, NewsPageBase.class);
    }

    public PlayersPageBase openPlayersPage(){
        playersButton.hover();
        playersButton.click();
        return initPage(driver, PlayersPageBase.class);
    }
}
