package com.zebrunner.carina.ui;

import com.zebrunner.carina.ui.components.footer.FooterMenu;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = ProductsPageBase.class)
public class ProductsPage extends ProductsPageBase {

    @FindBy(css = "footer[class*='footer']")
    private FooterMenu footerMenu;

    @FindBy(xpath = "//div[contains(@class, 'inventory_item')]")
    private List<ExtendedWebElement> productItems;

    @FindBy(xpath = "//button[contains(text(),'Add to cart')]")
    private ExtendedWebElement addToCartButton;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductListDisplayed() {
        return productItems.size() == 6;
    }

    public List<ExtendedWebElement> getProductItems() {
        return productItems;
    }

    public void addFirstProductToCart() {
        if (!productItems.isEmpty()) {
            addToCartButton.click();
        }
    }

    @Override
    public FooterMenu getFooterMenu() {
        return this.footerMenu;
    }
}

