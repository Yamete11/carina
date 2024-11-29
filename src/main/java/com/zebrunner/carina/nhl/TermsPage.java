package com.zebrunner.carina.nhl;

import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = TermsPageBase.class)
public class TermsPage extends TermsPageBase{
    public TermsPage(WebDriver driver) {
        super(driver);
    }
}
