package com.zebrunner.carina.nhl.android;

import com.zebrunner.carina.nhl.HomePageBase;
import com.zebrunner.carina.nhl.SearchPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = SearchPageBase.class)
public class SearchPageMobile extends SearchPageBase {
    public SearchPageMobile(WebDriver driver) {
        super(driver);
    }
}
