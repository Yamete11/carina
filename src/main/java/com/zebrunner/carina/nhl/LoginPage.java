package com.zebrunner.carina.nhl;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase{

    @FindBy(xpath = "//div[@class='sc-eDWCr hwfPfi  input-wrapper']//input[@name=\"email\"]")
    private ExtendedWebElement login;

    @FindBy(xpath = "//div[@class='sc-eDWCr hwfPfi  input-wrapper']//input[@name='password']")
    private ExtendedWebElement password;

    @FindBy(xpath = "//form[@class='sc-hHTYSt UJRKf profile-form']//button")
    private ExtendedWebElement signInButton;

    @FindBy(xpath = "//div[@class='header']//h1")
    private ExtendedWebElement pageTitle;


    public LoginPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(pageTitle);
    }

    @Override
    public boolean isPageOpened() {
        return pageTitle.isElementPresent();
    }

    public void signIn(String email, String pass){
        login.type(email);
        password.type(pass);
        signInButton.click();
    }
}
