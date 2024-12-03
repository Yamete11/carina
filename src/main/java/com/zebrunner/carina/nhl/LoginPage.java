package com.zebrunner.carina.nhl;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
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


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void signIn(){
        login.type("glebasher11@gmail.com");
        password.type("Password123");
        signInButton.click();
    }
}
