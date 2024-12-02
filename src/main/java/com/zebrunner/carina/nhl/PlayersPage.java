package com.zebrunner.carina.nhl;

import com.zebrunner.carina.nhl.components.Player;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = PlayersPageBase.class)
public class PlayersPage extends PlayersPageBase{
    @FindBy(xpath = "//a[@class='sc-eyCcmj jMLklB']")
    private List<Player> players;

    @FindBy(xpath = "//a[@class='sc-eyCcmj jMLklB']")
    private List<ExtendedWebElement> playersLinks;

    public PlayersPage(WebDriver driver) {
        super(driver);
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
