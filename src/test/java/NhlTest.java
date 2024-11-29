import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.nhl.HomePageBase;
import com.zebrunner.carina.nhl.NewsPage;
import com.zebrunner.carina.nhl.PlayersPage;
import com.zebrunner.carina.nhl.TermsPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NhlTest implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

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

    @Test
    public void testStories(){
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        assertTrue(homePage.isPageOpened(), "Home page is not displayed");

        List<ExtendedWebElement> stories = homePage.getStories();
        assertEquals(stories.size(), 9, "Spotlight is empty");
        for (ExtendedWebElement story : stories) {
            String storyTitle = story.getText().trim();
            assertTrue(storyTitle.length() > 0, "Story is empty: " + story);
            LOGGER.info("Verified story with text: " + storyTitle);
        }
        LOGGER.info("Number of stories equals: " + stories.size());
    }

    @Test
    public void testPlayers(){
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        assertTrue(homePage.isPageOpened(), "Home page is not displayed");
        PlayersPage playersPage = homePage.openPlayersPage();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(driver -> playersPage.getPlayers().size() > 0);

        List<ExtendedWebElement> players = playersPage.getPlayers();

        assertEquals(players.size(), 10, "Expected 10 players, but got: " + players.size());
        LOGGER.info("Number of players equals: " + players.size());

        for (ExtendedWebElement player : players) {
            String playerText = player.getText().trim();
            assertTrue(playerText.length() > 0, "Player's name is empty: " + player);
            LOGGER.info("Verified story with text: " + playerText);
        }
    }

    @Test
    public void testContactUsPage(){
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        assertTrue(homePage.isPageOpened(), "Home page is not displayed");

        homePage.openContactUsPage();

        assertEquals(getDriver().getCurrentUrl(), "https://www.nhl.com/info/contact-us", "Wrong page URL!");

    }


}
