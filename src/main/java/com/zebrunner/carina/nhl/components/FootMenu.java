package com.zebrunner.carina.nhl.components;

import com.zebrunner.carina.nhl.TermsPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class FootMenu extends AbstractUIObject {


    @FindBy(xpath = "./section/div/div[1]/nav[2]/ul/li[1]/a")
    private ExtendedWebElement termsButton;

    public FootMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public TermsPage openTerms(){
        termsButton.scrollTo();
        termsButton.hover();
        termsButton.click();
        return new TermsPage(driver);
    }
}
