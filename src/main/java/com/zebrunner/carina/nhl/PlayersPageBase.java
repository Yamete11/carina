package com.zebrunner.carina.nhl;

import com.zebrunner.carina.nhl.components.Player;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public abstract class PlayersPageBase extends AbstractPage {

    @FindBy(xpath = "//*[@id=\"onetrust-accept-btn-handler\"]")
    private ExtendedWebElement acceptCookieButton;

    public PlayersPageBase(WebDriver driver) {
        super(driver);
    }

    public void acceptCookies() {
        acceptCookieButton.clickIfPresent(3);
    }

    public abstract List<Player> getPlayers();
    public abstract List<ExtendedWebElement> getPlayersLinks();
    public abstract SinglePlayerPageBase openLink(ExtendedWebElement link);

}
