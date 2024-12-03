package com.zebrunner.carina.nhl.components;

import com.zebrunner.carina.nhl.*;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FootMenu extends AbstractUIObject {


    @FindBy(xpath = "//a[@href='/info/terms-of-service']")
    private ExtendedWebElement termsButton;

    @FindBy(xpath = "//button[@data-js-button-footer-dropdown]\n")
    private ExtendedWebElement aboutButton;

    @FindBy(xpath = "//a[@href='/info/contact-us']")
    private ExtendedWebElement contactUsButton;

    public FootMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }



    public ContactUsPageBase openContactUsPage(){
        aboutButton.scrollTo();
        aboutButton.hover();
        aboutButton.click();
        contactUsButton.hover();
        contactUsButton.click();
        return initPage(driver, ContactUsPageBase.class);

    }
}
