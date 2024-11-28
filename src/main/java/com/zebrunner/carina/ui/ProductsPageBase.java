package com.zebrunner.carina.ui;

import com.zebrunner.carina.ui.components.footer.FooterMenu;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class ProductsPageBase extends AbstractPage {
    public ProductsPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract FooterMenu getFooterMenu();

    public abstract boolean isProductListDisplayed();

    public abstract List<ExtendedWebElement> getProductItems();
}
