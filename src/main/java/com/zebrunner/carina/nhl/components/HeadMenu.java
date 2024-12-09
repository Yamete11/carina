package com.zebrunner.carina.nhl.components;

import com.zebrunner.carina.nhl.*;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class HeadMenu extends AbstractUIObject {

    @FindBy(xpath = ".//button[@class='nhl-o-menu__link']")
    private ExtendedWebElement languageButton;

    @FindBy(xpath = ".//ul[@class='nhl-o-dropdown__menu nhl-o-menu -vertical']//li[@class='nhl-o-menu__item' and @lang]")
    private List<ExtendedWebElement> listLanguageButton;

    public HeadMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void open(HeadLinks link) {
        ExtendedWebElement button = findExtendedWebElement(By.xpath(link.getXpath()));
        button.hover();
        button.click();
    }


    public void switchLanguage(String lang) {
        languageButton.hover();
        languageButton.click();
        for (ExtendedWebElement item : listLanguageButton) {
            if(item.getAttribute("lang").equals(lang)){
                ExtendedWebElement link = item.findExtendedWebElement(By.xpath(".//a"));
                link.hover();
                link.click();
                break;
            }
        }
    }

}
