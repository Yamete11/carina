package com.zebrunner.carina.nhl;

import com.zebrunner.carina.nhl.components.FootMenu;
import com.zebrunner.carina.nhl.components.HeadMenu;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase{

    @FindBy(className = "nhl-c-footer")
    private FootMenu footerMenu;

    @FindBy(className = "nhl-c-header")
    private HeadMenu headerMenu;

    @FindBy(xpath = "//*[@id=\"onetrust-accept-btn-handler\"]")
    private ExtendedWebElement acceptCookieButton;


    public HomePage(WebDriver driver) {
        super(driver);
    }


    public HeadMenu getHeaderMenu() {
        return headerMenu;
    }

    public NewsPage openNewsPage(){
        return getHeaderMenu().openNewsPage();
    }


    public FootMenu getFooterMenu() {
        return footerMenu;
    }

    public TermsPage openTermsPage() {
        return getFooterMenu().openTerms();
    }
}
