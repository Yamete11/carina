package com.zebrunner.carina.nhl;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class LoginPageBase extends AbstractPage {
    public LoginPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void signIn(String email, String pass);
    public abstract ExtendedWebElement getErrorTitle();
    public abstract List<ExtendedWebElement> getFieldsError();
}
