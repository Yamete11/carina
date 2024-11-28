package com.zebrunner.carina.ui.playground;

import com.zebrunner.carina.ui.LoginPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {

    @FindBy(css = "div.col-sm")
    private List<ExtendedWebElement> colSmElements;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public List<ColSm> getColSmItems() {
        List<ColSm> colSmItems = new ArrayList<>();
        for (ExtendedWebElement element : colSmElements) {
            colSmItems.add(new ColSm(element.getDriver()));
        }
        return colSmItems;
    }
}
