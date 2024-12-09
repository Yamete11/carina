package com.zebrunner.carina.nhl.android;

import com.zebrunner.carina.nhl.HomePageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HomePageBase.class)
public class HomePageMobile extends HomePageBase {

    public HomePageMobile(WebDriver driver) {
        super(driver);
    }
}
