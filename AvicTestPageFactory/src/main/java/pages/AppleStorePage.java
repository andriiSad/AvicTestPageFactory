package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppleStorePage extends pages.BasePage {
    @FindBy(xpath = "//div[@class = 'brand-box__title']//a[text() = 'iPhone']")
    private WebElement iphonePageButton;

    public AppleStorePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToIphonePage() {
        explicitWaitVisibilityOf(iphonePageButton, 10);
        iphonePageButton.click();
    }
}
