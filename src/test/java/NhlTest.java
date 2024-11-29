import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.nhl.HomePageBase;
import com.zebrunner.carina.nhl.NewsPage;
import com.zebrunner.carina.nhl.TermsPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NhlTest implements IAbstractTest {

    @Test
    @MethodOwner(owner = "qpsdemo")
    public void testHomePage() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();

        assertEquals(getDriver().getCurrentUrl(), "https://www.nhl.com/", "Wrong page URL!");
    }

    @Test
    @MethodOwner(owner = "qpsdemo")
    public void testNewsPage() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.openNewsPage();

        assertEquals(getDriver().getCurrentUrl(), "https://www.nhl.com/news/", "Wrong page URL!");
    }

    @Test
    public void testTermsButton(){
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        assertTrue(homePage.isPageOpened(), "Home page is not displayed");
        homePage.openTermsPage();
        assertEquals(getDriver().getCurrentUrl(), "https://www.nhl.com/info/terms-of-service", "Wrong page URL!");
    }

    @Test
    public void testTermsButtonFromNews(){
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        assertTrue(homePage.isPageOpened(), "Home page is not displayed");
        NewsPage newsPage = homePage.openNewsPage();
        newsPage.openTermsPage();
        assertEquals(getDriver().getCurrentUrl(), "https://www.nhl.com/info/terms-of-service", "Wrong page URL!");
    }


}
