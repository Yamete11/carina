package com.zebrunner.carina.nhl;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public abstract class LoginPageBase extends AbstractPage {

    @FindBy(xpath = "//*[@id=\"onetrust-accept-btn-handler\"]")
    private ExtendedWebElement acceptCookieButton;
    public LoginPageBase(WebDriver driver) {
        super(driver);
    }

    public void acceptCookies() {
        acceptCookieButton.clickIfPresent(3);
    }

    public abstract void signIn(String email, String pass);
    public abstract ExtendedWebElement getErrorTitle();
    public abstract List<ExtendedWebElement> getFieldsError();
}
