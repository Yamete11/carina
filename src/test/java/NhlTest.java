import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.nhl.*;
import com.zebrunner.carina.nhl.components.Player;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import org.slf4j.Logger;
import org.testng.asserts.SoftAssert;

import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.List;

import static com.zebrunner.carina.nhl.components.HeadLinks.*;
import static org.testng.Assert.*;

public class NhlTest implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private HomePageBase homePage;

    @BeforeMethod
    public void setup() {
        homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.acceptCookies();
    }

    @Test
    public void checkHeader(){
        homePage.getHeaderMenu().open(PLAYERS);
        LOGGER.info(getDriver().getCurrentUrl());
        PlayersPageBase newsPageBase = initPage(getDriver(), PlayersPageBase.class);
        assertTrue(newsPageBase.isPageOpened(), "Wrong page");
    }


    @Test
    @MethodOwner(owner = "demo")
    public void testPlayersConsistency() {
        homePage.getHeaderMenu().open(PLAYERS);
        PlayersPageBase playersPage = initPage(getDriver(), PlayersPageBase.class);
        assertEquals(getDriver().getCurrentUrl(), R.CONFIG.get("playersUrl"), "Wrong page URL!");

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(driver -> !playersPage.getPlayers().isEmpty());

        List<Player> players = playersPage.getPlayers();

        assertEquals(players.size(), 10, "Expected 10 players, but got: " + players.size());
        LOGGER.info("Number of players equals: " + players.size());

        for (Player player : players) {
            SoftAssert softAssert = new SoftAssert();

            String playerName = player.getPlayerName().getText().trim();
            LOGGER.info("Player's name: " + playerName);
            softAssert.assertTrue(playerName.length() > 0, "The player's name is empty");

            String numberText = player.getNumber().getText().trim();
            LOGGER.info("Number : " + numberText);
            softAssert.assertTrue(numberText.length() > 0, "The player's number is empty");

            String positionText = player.getPosition().getText().trim();
            String positionLastTwo = positionText.substring(numberText.length()).trim();
            LOGGER.info("Position : " + positionLastTwo);
            softAssert.assertTrue(positionLastTwo.length() > 0, "The player's position is empty");

            String imgSrc = player.getTeamImg().getAttribute("src");
            LOGGER.info("Team image source: " + imgSrc);
            softAssert.assertTrue(imgSrc != null && !imgSrc.isEmpty(), "The player's team image source is empty or null");


            softAssert.assertAll();
        }
    }

    @Test
    @MethodOwner(owner = "demo")
    public void testPlayersLinks() {
        homePage.getHeaderMenu().open(PLAYERS);
        PlayersPageBase playersPage = initPage(getDriver(), PlayersPageBase.class);
        assertEquals(getDriver().getCurrentUrl(), R.CONFIG.get("playersUrl"), "Wrong page URL!");

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(driver -> !playersPage.getPlayers().isEmpty());

        int playersSize = playersPage.getPlayersLinks().size();


        SoftAssert softAssert = new SoftAssert();
        for(int i = 0; i < playersSize; i++){

            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(driver -> !playersPage.getPlayers().isEmpty());

            List<ExtendedWebElement> player = playersPage.getPlayersLinks();

            String text = player.get(i).getText();
            SinglePlayerPageBase singlePlayerPage =  playersPage.openLink(player.get(i));

            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(driver -> singlePlayerPage.getPlayerName().isVisible());

            softAssert.assertTrue(text.contains(singlePlayerPage.getPlayerName().getText().trim()), "Player name does not match the link text!");

            getDriver().navigate().back();
        }

        softAssert.assertAll();
    }

    @Test
    @MethodOwner(owner = "demo")
    public void testStories(){
        int storiesSize = homePage.getStories().size();

        SoftAssert softAssert = new SoftAssert();

        for(int i = 0; i < storiesSize; i++){
            List<ExtendedWebElement> story = homePage.getStories();

            StoryPageBase storyPage = homePage.openStory(story.get(i));

            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(driver -> storyPage.getPageTitle().isVisible());

            assertTrue(storyPage.getPageTitle().getText().trim().length() > 0, "Story page title is empty");

            LOGGER.info("Verified story with text: " + storyPage.getPageTitle().getText());

            getDriver().navigate().back();

        }
        softAssert.assertAll();
    }

    @DataProvider(name = "languageData")
    public Object[][] createLanguageData() {
        return new Object[][]{
                {"en", "Top Stories"},
                {"fr", "Nouvelles récentes"},
                {"de", "Top Stories"},
                {"fi", "Suosituimmat jutut"},
                {"sv", "Toppnyheter"},
                {"cs", "Hlavní zprávy"},
                {"sk", "Hlavné správy"},
                {"es", "Titulares"}
        };
    }

    @Test(dataProvider = "languageData")
    @MethodOwner(owner = "demo")
    public void testLanguage(String lang, String checkTitle){
        homePage.getHeaderMenu().switchLanguage(lang);
        LOGGER.info(getDriver().getCurrentUrl());
        assertEquals(homePage.getTopStoriesTitle().getText(), checkTitle, "Wrong page");
    }



    @DataProvider(name = "userData")
    @MethodOwner(owner = "demo")
    public Object[][] createUserData() {
        return new Object[][]{
                {R.TESTDATA.get("user.username") + "11@gmail.com", R.TESTDATA.get("user.password"), LoginOutcome.SUCCESS},
                {"invalid123", "invalid123", LoginOutcome.INVALID_CREDENTIALS},
                {"123", "123", LoginOutcome.FIELD_ERRORS}
        };
    }

    @Test(dataProvider = "userData")
    @MethodOwner(owner = "demo")
    public void testSignIn(String username, String password, LoginOutcome outcome) {
        homePage.getHeaderMenu().open(LOGIN);
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
        loginPage.signIn(username, password);

        switch (outcome) {
            case SUCCESS:
                new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                        .until(driver -> driver.getCurrentUrl().contains(homePage.getExpectedUrlPart()));

                LOGGER.info("Current URL after sign in: " + getDriver().getCurrentUrl());
                assertTrue(homePage.isPageOpened(), "Home page is not displayed after sign-in");

                homePage.getHeaderMenu().open(PROFILE);
                ProfilePageBase profilePageBase = initPage(getDriver(), ProfilePageBase.class);
                assertTrue(profilePageBase.isPageOpened(), "Profile page is not displayed");
                break;

            case INVALID_CREDENTIALS:
                assertTrue(loginPage.getErrorTitle().isVisible(), "Error message is not displayed for invalid credentials");
                break;

            case FIELD_ERRORS:
                List<ExtendedWebElement> errors = loginPage.getFieldsError();

                SoftAssert softAssert = new SoftAssert();
                for (ExtendedWebElement error : errors) {
                    softAssert.assertTrue(error.isVisible(), "Error is not visible");
                }
                softAssert.assertAll();
                break;

            default:
                throw new IllegalArgumentException("Unsupported login outcome: " + outcome);
        }
    }


    @Test
    @MethodOwner(owner = "demo")
    public void testInvalidSearch(){
        homePage.getHeaderMenu().open(SEARCH);
        SearchPageBase searchPage =  initPage(getDriver(), SearchPageBase.class);

        assertTrue(searchPage.isPageOpened(), "Wrong URL");

        searchPage.enterQuery("asdasdasdasd");
        assertEquals(searchPage.getSearchSubTitle().getText().trim(), "Sorry, no matches were found with \"asdasdasdasd\". Try a new search", "Wrong output");
    }

}
