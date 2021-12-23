package avic_tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTests extends BaseTest {
    private static final int QUANTITY_OF_PRODUCTS_ADDED_TO_CART = 15;

    @Test
    public void checkThatPriceIsAddingCorrectlyInCart(){
        getHomePage().navigateToAppleStorePage();
        getAppleStorePage().navigateToIphonePage();

        getIphonePage().addProductToCart();

        Assert.assertTrue(getCartPage().compareTotalPriceAndOneItemPrice(QUANTITY_OF_PRODUCTS_ADDED_TO_CART));
        //як я розумію тут якись баг, бо працює через раз і це не через очікування,
        // просто при нажатті на + не змінюється тотал ціна,
        // хоча наступний + не нажимається поки тотал ціна не зміниться.
        //Expected condition failed: waiting for text ('47998 грн')
    }
}
