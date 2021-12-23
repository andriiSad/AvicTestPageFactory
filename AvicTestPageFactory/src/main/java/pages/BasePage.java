package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void implicitWait(long timeToWait) {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(timeToWait));
    }

    public void explicitWaitVisibilityOf(WebElement webElement, long timeOutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds)).until(ExpectedConditions.visibilityOf(webElement));
    }

    public void explicitWaitTextToBePresentInElement(WebElement webElement, long timeOutInSeconds, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds)).until(ExpectedConditions.textToBePresentInElement(webElement, text));
    }


    public String getCssValueOfElement(WebElement webElement, String propertyName) {
        return webElement.getCssValue(propertyName);
    }

    public int parseStringPriceToIntegerPrice(String stringPrice) {
        return Integer.parseInt(stringPrice.replaceAll("[^0-9]", ""));
    }

    public List<Integer> mapWebElementPriceListToIntegerPriceList(List<WebElement> webElementPriceList) {
        return webElementPriceList.stream()
                .filter(e -> !e.getText().equals(""))
                .map(e -> parseStringPriceToIntegerPrice(e.getText()))
                .collect(Collectors.toList());
    }


}
