package com.zebrunner.carina.nhl.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Player extends AbstractUIObject {

    @FindBy(xpath = "./span")
    private ExtendedWebElement playerName;

    @FindBy(xpath = "./div[2]/div[1]/div/div/img")
    private ExtendedWebElement teamImg;

    @FindBy(xpath = "./div[2]")
    private ExtendedWebElement position;

    @FindBy(xpath = "./div[2]/div[2]")
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
