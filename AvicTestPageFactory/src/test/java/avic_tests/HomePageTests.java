package avic_tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {
    private static final int EXPECTED_AMOUNT_OF_PARTNERS = 5;

    @Test
    public void checkThatNumberOfPartnersOnMainPageIsValid() {
        Assert.assertEquals(getHomePage().getPartnersListCount(), EXPECTED_AMOUNT_OF_PARTNERS);
    }

    @Test
    public void checkThatNewPriceIsLowerThanOldPrice() {
        for (Boolean condition : getHomePage().getConformityListForOldNew()) {
            Assert.assertTrue(condition);
        }
    }

    @Test
    public void checkThatPopUpAddCloses() {
        getHomePage().closePopUpAdd();
    }
}
