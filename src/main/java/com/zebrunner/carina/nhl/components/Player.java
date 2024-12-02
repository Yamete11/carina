package com.zebrunner.carina.nhl.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Player extends AbstractUIObject {

    @FindBy(xpath = ".//span[@class='sc-ekcpMq gepVpB']")
    private ExtendedWebElement playerName;

    @FindBy(xpath = ".//img[@class='sc-jiSpbx fHEaeZ team-logo']")
    private ExtendedWebElement teamImg;

    @FindBy(xpath = ".//div[@class='sc-dlunQd gULoAA']")
    private ExtendedWebElement position;

    @FindBy(xpath = ".//div[@class='sc-cWUBqk ekHWbO']")
    private ExtendedWebElement number;

    public Player(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }


    public ExtendedWebElement getPlayerName() {
        return playerName;
    }

    public ExtendedWebElement getTeamImg() {
        return teamImg;
    }

    public ExtendedWebElement getPosition() {
        return position;
    }

    public ExtendedWebElement getNumber() {
        return number;
    }
}
