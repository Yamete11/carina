package nhl;

import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.nhl.PlayersPageBase;
import com.zebrunner.carina.nhl.SinglePlayerPageBase;
import com.zebrunner.carina.nhl.components.Player;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class PlayerTest extends DesktopTest {

    private PlayersPageBase playersPage;

    @BeforeMethod
    public void setup() {
        playersPage = initPage(getDriver(), PlayersPageBase.class);
        playersPage.open();
        playersPage.acceptCookies();
    }

    @Test
    @MethodOwner(owner = "demo")
    public void testPlayersConsistency() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(driver -> !playersPage.getPlayers().isEmpty());

        List<Player> players = playersPage.getPlayers();

        assertEquals(players.size(), 10, "Expected 10 players, but got: " + players.size());
        LOGGER.info("Number of players equals: " + players.size());
        SoftAssert softAssert = new SoftAssert();

        for (Player player : players) {
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
        }
        softAssert.assertAll();

    }

    @Test
    @MethodOwner(owner = "demo")
    public void testPlayersLinks() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(driver -> !playersPage.getPlayers().isEmpty());

        int playersSize = playersPage.getPlayersLinks().size();

        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < playersSize; i++) {

            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(driver -> !playersPage.getPlayers().isEmpty());

            List<ExtendedWebElement> player = playersPage.getPlayersLinks();

            String text = player.get(i).getText();
            SinglePlayerPageBase singlePlayerPage = playersPage.openLink(player.get(i));

            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(driver -> singlePlayerPage.getPlayerName().isVisible());

            softAssert.assertTrue(text.contains(singlePlayerPage.getPlayerName().getText().trim()), "Player name does not match the link text!");

            getDriver().navigate().back();
        }

        softAssert.assertAll();
    }
}
