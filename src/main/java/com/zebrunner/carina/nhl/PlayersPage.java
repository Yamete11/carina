package com.zebrunner.carina.nhl;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = PlayersPageBase.class)
public class PlayersPage extends PlayersPageBase{
    @FindBy(xpath = "//*[@id=\"root\"]/div[2]/div/div/div[3]/a/span")
    List<ExtendedWebElement> players;

    public PlayersPage(WebDriver driver) {
        super(driver);
    }

    public List<ExtendedWebElement> getPlayers() {
        return players;
    }
}
