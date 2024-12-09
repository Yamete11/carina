package com.zebrunner.carina.nhl;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    @FindBy(xpath = "//div[@class='sc-dkrFOg byGefN message-container']")
    private ExtendedWebElement errorTitle;

    @FindBy(xpath = "//div[@class='sc-ksBlkl iwKhDT error-message']")
    private List<ExtendedWebElement> fieldsError;



    public LoginPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL("https://account.nhl.com/ui/?client_id=a2nhl&lang=en&redirect_uri=https%3A%2F%2Faccount.nhl.com%2Fui%2F&returnUrl=https%3A%2F%2Fwww.nhl.com%2F");
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(pageTitle);
    }

    @Override
    public boolean isPageOpened() {
        return pageTitle.isElementPresent();
    }

    @Override
    public ExtendedWebElement getErrorTitle() {
        return errorTitle;
    }

    @Override
    public List<ExtendedWebElement> getFieldsError() {
        return fieldsError;
    }

    @Override
    public void signIn(String email, String pass){
        login.type(email);
        password.type(pass);
        signInButton.click();
    }
}
