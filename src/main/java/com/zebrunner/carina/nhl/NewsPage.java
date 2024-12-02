package com.zebrunner.carina.nhl;

import com.zebrunner.carina.nhl.components.FootMenu;
import com.zebrunner.carina.nhl.components.HeadMenu;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = NewsPageBase.class)
public class NewsPage extends NewsPageBase{

    @FindBy(className = "nhl-c-footer")
    private FootMenu footerMenu;

    @FindBy(className = "nhl-c-header")
    private HeadMenu headerMenu;


    public NewsPage(WebDriver driver) {
        super(driver);
    }

    public HeadMenu getHeaderMenu() {
        return headerMenu;
    }

    public NewsPageBase openNewsPage(){
        return getHeaderMenu().openNewsPage();
    }

    public FootMenu getFooterMenu() {
        return footerMenu;
    }

    public TermsPageBase openTermsPage() {
        return getFooterMenu().openTerms();
    }
}
