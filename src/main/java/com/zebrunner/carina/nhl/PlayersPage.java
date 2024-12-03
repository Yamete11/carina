package com.zebrunner.carina.nhl;

import com.zebrunner.carina.nhl.components.Player;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = PlayersPageBase.class)
public class PlayersPage extends PlayersPageBase{
    @FindBy(xpath = "//a[@class='sc-eyCcmj jMLklB']")
    private List<Player> players;

    @FindBy(xpath = "//a[@class='sc-eyCcmj jMLklB']")
    private List<ExtendedWebElement> playersLinks;

    @FindBy(xpath = "//div[@class='sc-egpspN foBIiO']//h1")
    private ExtendedWebElement pageTitle;

    public PlayersPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(pageTitle);
    }

    @Override
    public boolean isPageOpened() {
        return pageTitle.isElementPresent();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<ExtendedWebElement> getPlayersLinks() {
        return playersLinks;
    }

    public SinglePlayerPageBase openLink(ExtendedWebElement link){
        link.hover();
        link.click();
        return initPage(driver, SinglePlayerPageBase.class);
    }

}
