package com.zebrunner.carina.nhl;

import com.zebrunner.carina.nhl.components.FootMenu;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public abstract class HomePageBase extends AbstractPage {

    @FindBy(xpath = "//*[@id=\"onetrust-accept-btn-handler\"]")
    private ExtendedWebElement acceptCookieButton;


    public HomePageBase(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        super.open();
        acceptCookieButton.clickIfPresent(3);
    }

    public abstract NewsPage openNewsPage();
    public abstract TermsPage openTermsPage();

}
