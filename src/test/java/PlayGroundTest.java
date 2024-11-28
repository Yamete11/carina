import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.ui.LoginPageBase;
import com.zebrunner.carina.ui.playground.ColSm;
import com.zebrunner.carina.ui.playground.HomePage;
import com.zebrunner.carina.ui.playground.HomePageBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PlayGroundTest extends AbstractTest {

    @Test
    public void testColSmLinks() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not displayed");

        List<ColSm> colSmLinks = homePage.getColSmItems();
        System.out.println(colSmLinks);

        Assert.assertEquals(colSmLinks.size(), 26, "The number of colSm links is incorrect");
    }



}
