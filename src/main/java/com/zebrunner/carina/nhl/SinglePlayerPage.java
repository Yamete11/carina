package com.zebrunner.carina.nhl;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = SinglePlayerPageBase.class)
public class SinglePlayerPage extends SinglePlayerPageBase{

    @FindBy(xpath = "//div[contains(@class, 'sc-cmSatC lcJAMt')]//h1[not(@role)]")
    private ExtendedWebElement playerName;

    public SinglePlayerPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(playerName);
    }
    @Override
    public boolean isPageOpened() {
        return playerName.isElementPresent();
    }

    @Override
    public ExtendedWebElement getPlayerName() {
        return playerName;
    }
}
