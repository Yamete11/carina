package com.zebrunner.carina.nhl;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = StoryPageBase.class)
public class StoryPage extends StoryPageBase{

    @FindBy(xpath = "//h1[@class='nhl-c-article__title nhl-ty-heading--h2']")
    private ExtendedWebElement pageTitle;


    public StoryPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(pageTitle);
    }

    @Override
    public boolean isPageOpened() {
        return pageTitle.isElementPresent();
    }

    @Override
    public ExtendedWebElement getPageTitle() {
        return pageTitle;
    }
}
