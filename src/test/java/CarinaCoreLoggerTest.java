import java.lang.invoke.MethodHandles;
import java.util.List;

import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.ui.LoginPage;
import com.zebrunner.carina.ui.LoginPageBase;
import com.zebrunner.carina.ui.ProductsPage;
import com.zebrunner.carina.ui.ProductsPageBase;
import com.zebrunner.carina.ui.components.footer.FooterMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CarinaCoreLoggerTest extends AbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String TWITTER_URL = "https://x.com/saucelabs";
    private static final String FACEBOOK_URL = "https://www.facebook.com/saucelabs";
    private static final String LINKEDIN_URL = "https://www.linkedin.com/company/sauce-labs/";


    @Test
    public void testValidLogin() {
        LOGGER.info("Start login test");
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not displayed");
        ProductsPageBase productsPage = loginPage.login("standard_user", "secret_sauce");
        productsPage.open();
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html",
                "Current URL is not the expected Products Page URL");
        Assert.assertTrue(productsPage.isPageOpened(), "Products page is not displayed");
        System.out.println(productsPage.getProductItems());




        LOGGER.info("Login test completed successfully");
    }

    /*@Test
    public void testClickTwitterRedirect() {
        ProductsPageBase productsPage = initPage(getDriver(), ProductsPageBase.class);
        productsPage.open();
        FooterMenuBase footerMenuBase = initPage(productsPage.getDriver(), FooterMenuBase.class);
        footerMenuBase.clickTwitter();
        Assert.assertEquals(footerMenuBase.getCurrentUrl(), TWITTER_URL, "Twitter redirection URL is incorrect!");
    }*/

    @Test
    public void testReportingAppender(){
        LOGGER.info("logger test");
    }
}
