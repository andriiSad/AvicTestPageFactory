package avic_tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchResultsTests extends BaseTest {
    private static final String SEARCH_KEYWORD_IPHONE_13 = "iPhone 13";
    private static final String SEARCH_KEYWORD_IPHONE = "iPhone";
    private static final String SEARCH_KEYWORD_EARPODS = "Наушники Apple EarPods with Lighting Connector (MMTN2)";
    private static final String EXPECTED_QUERY = "query=iPhone";
    private static final String EXPECTED_HEADPHONES_PRICE = "699 грн";
    private static final int EXPECTED_AMOUNT_OF_PRODUCTS = 12;
    private static final String EXPECTED_BACKGROUND_COLOR = "rgba(255, 90, 2, 1)";

    @Test
    public void checkThatUrlContainsSearchWord() {
        getHomePage().searchByKeyWord(SEARCH_KEYWORD_IPHONE_13);
        Assert.assertTrue(getDriver().getCurrentUrl().contains(EXPECTED_QUERY));
    }

    @Test
    public void checkElementsAmountOnSearchPage() {
        getHomePage().searchByKeyWord(SEARCH_KEYWORD_IPHONE_13);
        getHomePage().implicitWait(30);
        Assert.assertEquals(getSearchResultsPage().getSearchResultsCount(), EXPECTED_AMOUNT_OF_PRODUCTS);
    }

    @Test
    public void checkThatSearchResultsContainsSearchWord() {
        getHomePage().searchByKeyWord(SEARCH_KEYWORD_IPHONE_13);
        for (WebElement webElement : getSearchResultsPage().getSearchResultsList()) {
            Assert.assertTrue(webElement.getText().contains(SEARCH_KEYWORD_IPHONE_13));
        }
    }

    @Test
    public void verifyThatPriceIsCorrectOnLightningEarpods() {
        getHomePage().searchByKeyWord(SEARCH_KEYWORD_EARPODS);
        Assert.assertEquals(getSearchResultsPage().getFirstItemPrice(), EXPECTED_HEADPHONES_PRICE);
    }

    @Test
    public void checkThatEmailWidgetGotValidBackgroundColor() {
        getHomePage().navigateToAppleStorePage();
        getAppleStorePage().navigateToIphonePage();
        Assert.assertEquals(getIphonePage().getCssValueOfEmailWidget(), EXPECTED_BACKGROUND_COLOR);
    }

    @Test
    public void checkThatPriceFilterFormIsWorking() {
        getHomePage().searchByKeyWord(SEARCH_KEYWORD_IPHONE);

        for (boolean condition : getSearchResultsPage().getConformityListForRangeTest()) {
            Assert.assertTrue(condition);
        }
    }
}
