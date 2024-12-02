package com.zebrunner.carina.nhl;

import com.zebrunner.carina.nhl.components.Player;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = PlayersPageBase.class)
public class PlayersPage extends PlayersPageBase{
    @FindBy(xpath = "//*[@id=\"root\"]/div[2]/div/div/div[3]/a/span")
    private List<ExtendedWebElement> players;

    @FindBy(xpath = "//*[@id=\"root\"]/div[2]/div/div/div[3]/a[1]")
    private Player player;

    public PlayersPage(WebDriver driver) {
        super(driver);
    }

    public List<ExtendedWebElement> getPlayers() {
        return players;
    }

    public Player getPlayer() {
        return player;
    }
}
