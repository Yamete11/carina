package com.zebrunner.carina.nhl.components;

import com.zebrunner.carina.nhl.*;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.HashSet;

public class HeadMenu extends AbstractUIObject {

    @FindBy(xpath = "//a[@href='/news']")
    private ExtendedWebElement newsButton;

    @FindBy(xpath = "//a[@href='/player']")
    private ExtendedWebElement playersButton;

    @FindBy(xpath = "//button[@class='nhl-o-menu__link']")
    private ExtendedWebElement languageButton;

    @FindBy(xpath = "//li[@lang='sv']//a[@class='nhl-o-menu__link ']")
    private ExtendedWebElement svLanguageButton;

    @FindBy(xpath = "//a[@class='nhl-o-menu__link' and @title='Sign In']")
    private ExtendedWebElement loginButton;


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

    public LoginPageBase openLoginPage(){
        loginButton.hover();
        loginButton.click();
        return initPage(driver, LoginPageBase.class);
    }

    public void switchLanguage(){
        languageButton.hover();
        languageButton.click();
        svLanguageButton.hover();
        svLanguageButton.click();
    }
}
