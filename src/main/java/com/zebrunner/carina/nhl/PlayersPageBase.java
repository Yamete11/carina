package com.zebrunner.carina.nhl;

import com.zebrunner.carina.nhl.components.Player;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class PlayersPageBase extends AbstractPage {

    public PlayersPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract List<ExtendedWebElement> getPlayers();

    public abstract Player getPlayer();
}
