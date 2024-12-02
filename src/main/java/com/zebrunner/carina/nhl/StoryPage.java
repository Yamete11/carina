package com.zebrunner.carina.nhl;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = StoryPageBase.class)
public class StoryPage extends StoryPageBase{

    @FindBy(xpath = "//h1[@class='nhl-c-article__title nhl-ty-heading--h2']")
    private ExtendedWebElement pageTitle;


    public StoryPage(WebDriver driver) {
        super(driver);
    }

    public ExtendedWebElement getPageTitle() {
        return pageTitle;
    }
}
