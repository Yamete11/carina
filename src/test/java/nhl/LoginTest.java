package nhl;

import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.nhl.HomePageBase;
import com.zebrunner.carina.nhl.LoginPageBase;
import com.zebrunner.carina.nhl.ProfilePageBase;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

import static com.zebrunner.carina.nhl.components.HeadLinks.PROFILE;
import static org.testng.Assert.assertTrue;

public class LoginTest extends DesktopTest {

    private LoginPageBase loginPage;

    @BeforeMethod
    public void setup() {
        loginPage = initPage(getDriver(), LoginPageBase.class);
        loginPage.open();
        loginPage.acceptCookies();
    }

    @Test
    @MethodOwner(owner = "demo")
    public void testSuccessfulSignIn() {
        String username = R.TESTDATA.get("user.username") + "11@gmail.com";
        String password = R.TESTDATA.get("user.password");

        loginPage.signIn(username, password);

        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(driver -> driver.getCurrentUrl().contains(homePage.getExpectedUrlPart()));

        LOGGER.info("Current URL after sign in: " + getDriver().getCurrentUrl());
        assertTrue(homePage.isPageOpened(), "Home page is not displayed after sign-in");

        homePage.getHeaderMenu().open(PROFILE);
        ProfilePageBase profilePageBase = initPage(getDriver(), ProfilePageBase.class);
        assertTrue(profilePageBase.isPageOpened(), "Profile page is not displayed");
    }

    @Test
    @MethodOwner(owner = "demo")
    public void testInvalidCredentials() {
        String username = "invalid123";
        String password = "invalid123";

        loginPage.signIn(username, password);

        assertTrue(loginPage.getErrorTitle().isVisible(), "Error message is not displayed for invalid credentials");
    }

    @Test
    @MethodOwner(owner = "demo")
    public void testFieldErrors() {
        String username = "123";
        String password = "123";

        loginPage.signIn(username, password);

        List<ExtendedWebElement> errors = loginPage.getFieldsError();
        SoftAssert softAssert = new SoftAssert();

        for (ExtendedWebElement error : errors) {
            softAssert.assertTrue(error.isVisible(), "Error is not visible");
        }
        softAssert.assertAll();
    }
}
