package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IphonePage extends BasePage {
    @FindBy(xpath = "//div[@class = 'email-widget-bubble pp-visible']")
    private WebElement emailWidget;
    @FindBy(xpath = "//a[@class = 'prod-cart__buy']")
    WebElement buyButton;

    private static final String PROPERTY_NAME_BACKGROUND_COLOR = "background-color";

    public IphonePage(WebDriver driver) {
        super(driver);
    }

    public String getCssValueOfEmailWidget() {
        explicitWaitVisibilityOf(emailWidget, 10);
        return getCssValueOfElement(emailWidget, PROPERTY_NAME_BACKGROUND_COLOR);
    }

    public void addProductToCart() {
        buyButton.click();
    }

}
