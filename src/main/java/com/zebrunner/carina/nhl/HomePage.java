package com.zebrunner.carina.nhl;

import com.zebrunner.carina.nhl.components.FootMenu;
import com.zebrunner.carina.nhl.components.HeadMenu;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
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

    @FindBy(xpath = "//a[@class='nhl-o-menu__link nhl-c-headlines__link']")
    List<ExtendedWebElement> stories;

    @FindBy(xpath = "//h5[@class='nhl-o-heading nhl-c-headlines__title nhl-ty-heading--h4']")
    private ExtendedWebElement topStoriesTitle;


    public HomePage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(topStoriesTitle);
    }

    @Override
    public boolean isPageOpened() {
        return topStoriesTitle.isElementPresent();
    }


    @Override
    public HeadMenu getHeaderMenu() {
        return headerMenu;
    }

    @Override
    public NewsPageBase openNewsPage(){
        return getHeaderMenu().openNewsPage();
    }

    @Override
    public PlayersPageBase openPlayersPage(){
        return getHeaderMenu().openPlayersPage();
    }

    @Override
    public FootMenu getFooterMenu() {
        return footerMenu;
    }


    @Override
    public List<ExtendedWebElement> getStories() {
        return stories;
    }

    public StoryPageBase openStory(ExtendedWebElement link){
        link.hover();
        link.click();
        return initPage(driver, StoryPageBase.class);
    }

    public String getExpectedUrlPart(){
        return R.CONFIG.get("homeUrl");
    }

    public ExtendedWebElement getTopStoriesTitle() {
        return topStoriesTitle;
    }
}
