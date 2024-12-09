package nhl;

import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.nhl.HomePageBase;
import com.zebrunner.carina.nhl.SearchPageBase;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

import static com.zebrunner.carina.nhl.components.HeadLinks.SEARCH;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MobileTest implements IAbstractTest, IMobileUtils {

    protected static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    private HomePageBase homePage;

    @BeforeMethod
    public void setup() {
        homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.acceptCookies();
    }

    @Test()
    @MethodOwner(owner = "demo")
    public void testNhlPageOpens() {
        homePage.getHeaderMenu().open(SEARCH);
        SearchPageBase searchPage =  initPage(getDriver(), SearchPageBase.class);

        assertTrue(searchPage.isPageOpened(), "Wrong URL");

        searchPage.enterQuery("asdasdasdasd");
        assertEquals(searchPage.getSearchSubTitle().getText().trim(), "Sorry, no matches were found with \"asdasdasdasd\". Try a new search", "Wrong output");
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
}
