package com.zebrunner.carina.ui.components.footer;

import com.zebrunner.carina.ui.LoginPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class FooterMenu extends AbstractUIObject {

    @FindBy(xpath = "//ul/li/a[contains(@href, 'twitter')]")
    private ExtendedWebElement socialTwitter;

    @FindBy(xpath = "//ul/li/a[contains(@href, 'facebook')]")
    private ExtendedWebElement socialFacebook;

    @FindBy(xpath = "//ul/li/a[contains(@href, 'linkedin')]")
    private ExtendedWebElement socialLinkedin;
    public FooterMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void clickTwitter() {
        socialTwitter.click();
    }


    public void clickFacebook() {
        socialFacebook.click();
    }


    public void clickLinkedIn() {
        socialLinkedin.click();
    }
}
