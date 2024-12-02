package com.zebrunner.carina.nhl;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class NewsPageBase extends AbstractPage {
    public NewsPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract NewsPageBase openNewsPage();
    public abstract TermsPageBase openTermsPage();
}
