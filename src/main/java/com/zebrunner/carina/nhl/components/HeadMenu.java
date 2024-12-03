package com.zebrunner.carina.nhl.components;

import com.zebrunner.carina.nhl.*;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class HeadMenu extends AbstractUIObject {


    @FindBy(xpath = "//a[@href='/news']")
    private ExtendedWebElement newsButton;

    @FindBy(xpath = "//a[@href='/player']")
    private ExtendedWebElement playersButton;

    @FindBy(xpath = "//button[@class='nhl-o-menu__link']")
    private ExtendedWebElement languageButton;

    @FindBy(xpath = "//ul[@class='nhl-o-dropdown__menu nhl-o-menu -vertical']//li[@class='nhl-o-menu__item' and @lang]")
    private List<ExtendedWebElement> listLanguageButton;

    @FindBy(xpath = "//a[@class='nhl-o-menu__link' and @title='Sign In']")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = "//a[@class='nhl-o-menu__link' and @title='Profile']")
    private ExtendedWebElement profileButton;

    @FindBy(xpath = "//a[@class='nhl-c-header__btn nhl-c-header__btn--search' and @title='Search']")
    private ExtendedWebElement searchButton;


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

    public ProfilePageBase openProfilePage(){
        profileButton.hover();
        profileButton.click();
        return initPage(driver, ProfilePageBase.class);
    }

    public void switchLanguage(String lang) {
        languageButton.hover();
        languageButton.click();
        for (ExtendedWebElement item : listLanguageButton) {
            if(item.getAttribute("lang").equals(lang)){
                ExtendedWebElement link = item.findExtendedWebElement(By.xpath(".//a"));
                link.hover();
                link.click();
                break;
            }
        }
    }



    public SearchPageBase openSearchPage(){
        searchButton.hover();
        searchButton.click();
        return initPage(driver, SearchPageBase.class);
    }

    public ExtendedWebElement getLoginButton() {
        return loginButton;
    }

    public List<ExtendedWebElement> getListLanguageButton() {
        return listLanguageButton;
    }
}
