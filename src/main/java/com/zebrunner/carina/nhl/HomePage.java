package com.zebrunner.carina.nhl;

import com.zebrunner.carina.nhl.components.FootMenu;
import com.zebrunner.carina.nhl.components.HeadMenu;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase{

    @FindBy(className = "nhl-c-footer")
    private FootMenu footerMenu;

    @FindBy(className = "nhl-c-header")
    private HeadMenu headerMenu;

    @FindBy(xpath = "//*[@id=\"onetrust-accept-btn-handler\"]")
    private ExtendedWebElement acceptCookieButton;

    @FindBy(xpath = "//*[@id=\"main-content\"]/section/div[3]/section[1]/div/div[2]/div[1]/ul/li/a/span")
    List<ExtendedWebElement> stories;


    public HomePage(WebDriver driver) {
        super(driver);
    }


    public HeadMenu getHeaderMenu() {
        return headerMenu;
    }

    public NewsPage openNewsPage(){
        return getHeaderMenu().openNewsPage();
    }

    public PlayersPage openPlayersPage(){
        return getHeaderMenu().openPlayersPage();
    }


    public FootMenu getFooterMenu() {
        return footerMenu;
    }

    public TermsPage openTermsPage() {
        return getFooterMenu().openTerms();
    }

    public ContactUsPage openContactUsPage() {
        return getFooterMenu().openContactUsPage();
    }

    public List<ExtendedWebElement> getStories() {
        return stories;
    }
}
