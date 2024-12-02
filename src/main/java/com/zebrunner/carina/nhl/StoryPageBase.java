package com.zebrunner.carina.nhl;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class StoryPageBase extends AbstractPage {

    public StoryPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract ExtendedWebElement getPageTitle();
}
