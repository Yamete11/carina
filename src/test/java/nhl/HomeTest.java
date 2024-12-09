package nhl;

import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.nhl.HomePageBase;
import com.zebrunner.carina.nhl.SearchPageBase;
import com.zebrunner.carina.nhl.StoryPageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

import static com.zebrunner.carina.nhl.components.HeadLinks.SEARCH;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomeTest extends DesktopTest {

    private HomePageBase homePage;

    @BeforeMethod
    public void setup() {
        homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.acceptCookies();
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
