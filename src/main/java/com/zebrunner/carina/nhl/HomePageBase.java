package com.zebrunner.carina.nhl;

import com.zebrunner.carina.nhl.components.FootMenu;
import com.zebrunner.carina.nhl.components.HeadMenu;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public abstract class HomePageBase extends AbstractPage {

    @FindBy(xpath = "//*[@id=\"onetrust-accept-btn-handler\"]")
    private ExtendedWebElement acceptCookieButton;

    @FindBy(className = "nhl-c-footer")
    private FootMenu footerMenu;

    @FindBy(className = "nhl-c-header")
    private HeadMenu headerMenu;

    @CacheLookup
    @FindBy(xpath = "//a[@class='nhl-o-menu__link nhl-c-headlines__link']")
    List<ExtendedWebElement> stories;

    @FindBy(xpath = "//h5[@class='nhl-o-heading nhl-c-headlines__title nhl-ty-heading--h4']")
    private ExtendedWebElement topStoriesTitle;


    public HomePageBase(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(topStoriesTitle);
    }

    @Override
    public boolean isPageOpened() {
        return topStoriesTitle.isElementPresent();
    }

    @Override
    public void open() {
        super.open();
    }
    public void acceptCookies() {
        acceptCookieButton.clickIfPresent(3);
    }

    public List<ExtendedWebElement> getStories(){
        return stories;
    };
    public FootMenu getFooterMenu(){
        return footerMenu;
    };
    public HeadMenu getHeaderMenu(){
        return headerMenu;
    };
    public StoryPageBase openStory(ExtendedWebElement link){
        link.hover();
        link.click();
        return initPage(driver, StoryPageBase.class);
    };
    public String getExpectedUrlPart(){
        return R.CONFIG.get("homeUrl");
    };
    public ExtendedWebElement getTopStoriesTitle(){
        return topStoriesTitle;
    };

}
