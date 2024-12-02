package com.zebrunner.carina.nhl;

import com.zebrunner.carina.nhl.components.FootMenu;
import com.zebrunner.carina.nhl.components.HeadMenu;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = NewsPageBase.class)
public class NewsPage extends NewsPageBase{

    @FindBy(className = "nhl-c-footer")
    private FootMenu footerMenu;

    @FindBy(className = "nhl-c-header")
    private HeadMenu headerMenu;

    @FindBy(xpath = "//h2[@class='nhl-o-pattern-title__title nhl-ty-heading--h3 -center']")
    private ExtendedWebElement pageTitle;


    public NewsPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(pageTitle);
    }

    @Override
    public boolean isPageOpened() {
        return pageTitle.isPresent();
    }

    public HeadMenu getHeaderMenu() {
        return headerMenu;
    }

    public FootMenu getFooterMenu() {
        return footerMenu;
    }


}
