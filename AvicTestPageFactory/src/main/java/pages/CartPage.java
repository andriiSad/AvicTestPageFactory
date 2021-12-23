package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text() = '+']")
    WebElement plusButton;
    @FindBy(xpath = "//div[@class = 'item-total']//span[@class = 'prise']")
    WebElement currentValueWebElement;
    @FindBy(xpath = "//div[@class = 'total-h']//span")
    WebElement pricePerOneItemWebElement;
    @FindBy(xpath = "//div[@class = 'item-total']//span[@class = 'prise']")
    WebElement pricePerAllItemsWebElement;

    private int pricePerOneItemValue;
    private int pricePerAllItemsValue;

    public void clickOnPlusButtonNTimes(int quantity) {
        explicitWaitVisibilityOf(plusButton, 10);

        int currentValue = parseStringPriceToIntegerPrice(currentValueWebElement.getText());
        String value;

        for (int i = 2; i <= quantity; i++) {
            value = currentValue * i + " грн";
            plusButton.click();
            explicitWaitTextToBePresentInElement(currentValueWebElement, 5, value);
        }

        pricePerOneItemValue = parseStringPriceToIntegerPrice(pricePerOneItemWebElement.getText());
        pricePerAllItemsValue = parseStringPriceToIntegerPrice(pricePerAllItemsWebElement.getText());

    }

    public boolean compareTotalPriceAndOneItemPrice(int quantity) {
        clickOnPlusButtonNTimes(quantity);
        return pricePerOneItemValue * quantity == pricePerAllItemsValue;
    }

}
