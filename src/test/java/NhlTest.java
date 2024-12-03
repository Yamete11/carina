import com.github.kklisura.cdt.protocol.types.profiler.Profile;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.nhl.*;
import com.zebrunner.carina.nhl.components.HeadMenu;
import com.zebrunner.carina.nhl.components.Player;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import java.lang.invoke.MethodHandles;
import java.lang.module.Configuration;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NhlTest implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    //Test takes all the players from the page and check all its fields
    @Test
    @MethodOwner(owner = "demo")
    public void testPlayersConsistency() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.acceptCookies();
        PlayersPageBase playersPage = homePage.getHeaderMenu().openPlayersPage();
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

    //Test takes all the players from the page and checks its link
    @Test
    @MethodOwner(owner = "demo")
    public void testPlayersLinks() throws InterruptedException {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.acceptCookies();
        PlayersPageBase playersPage = homePage.getHeaderMenu().openPlayersPage();
        assertEquals(getDriver().getCurrentUrl(), R.CONFIG.get("playersUrl"), "Wrong page URL!");

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(driver -> !playersPage.getPlayers().isEmpty());

        List<ExtendedWebElement> playersLinks = playersPage.getPlayersLinks();

        assertEquals(playersLinks.size(), 10, "Expected 10 players, but got: " + playersLinks.size());
        LOGGER.info("Number of players equals: " + playersLinks.size());

        SoftAssert softAssert = new SoftAssert();
        for (ExtendedWebElement link : playersLinks) {
            String text = link.getText();
            SinglePlayerPageBase singlePlayerPage =  playersPage.openLink(link);

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
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.acceptCookies();
        assertTrue(homePage.isPageOpened(), "Home page is not displayed");

        List<ExtendedWebElement> stories = homePage.getStories();
        assertEquals(stories.size(), 9, "Spotlight is empty");
        LOGGER.info("Number of stories equals: " + stories.size());

        SoftAssert softAssert = new SoftAssert();
        for (ExtendedWebElement story : stories) {

            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(driver -> story.isVisible());

            String storyTitle = story.getText().trim();
            softAssert.assertTrue(storyTitle.length() > 0, "Story is empty: " + story);
            LOGGER.info("Verified story with text: " + storyTitle);


            StoryPageBase storyPage = homePage.openStory(story);

            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(driver -> storyPage.getPageTitle().isVisible());

            assertTrue(storyPage.getPageTitle().getText().trim().length() > 0, "Story page title is empty");

            LOGGER.info("Verified story with text: " + storyPage.getPageTitle().getText());

            storyPage.navigateBack();
        }
        softAssert.assertAll();
    }

    @Test
    @MethodOwner(owner = "demo")
    public void testLanguage(){
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.acceptCookies();
        assertTrue(homePage.isPageOpened(), "Home page is not displayed");

        homePage.getHeaderMenu().switchLanguage();

        assertEquals(getDriver().getCurrentUrl(), R.CONFIG.get("svLanguage"), "Wrong page");
    }

    @Test
    @MethodOwner(owner = "demo")
    public void testSingIn(){
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.acceptCookies();
        assertTrue(homePage.isPageOpened(), "Home page is not displayed");

        LoginPageBase loginPage = homePage.getHeaderMenu().openLoginPage();
        loginPage.signIn(R.TESTDATA.get("username") + "11@gmail.com", R.TESTDATA.get("password"));

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(driver -> driver.getCurrentUrl().contains(homePage.getExpectedUrlPart()));

        LOGGER.info("Current URL after sign in: " + getDriver().getCurrentUrl());

        assertTrue(homePage.isPageOpened(), "Home page is not displayed after sign-in");

        ProfilePageBase profilePageBase = homePage.getHeaderMenu().openProfilePage();

        assertTrue(profilePageBase.isPageOpened(), "Profile page is not displayed");
    }

    @Test
    @MethodOwner(owner = "demo")
    public void testInvalidSearch(){
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.acceptCookies();
        assertTrue(homePage.isPageOpened(), "Home page is not displayed");

        SearchPageBase searchPage =  homePage.getHeaderMenu().openSearchPage();

        assertTrue(searchPage.isPageOpened(), "Wrong URL");

        searchPage.enterQuery("asdasdasdasd");
        assertEquals(searchPage.getSearchSubTitle().getText().trim(), "Sorry, no matches were found with \"asdasdasdasd\". Try a new search", "Wrong output");
    }





}
