package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends BasePage {
    @FindBy(xpath = "//div[@class='prod-cart__descr']")
    List<WebElement> searchResultsProductsList;
    @FindBy(xpath = "//div[@class = 'prod-cart__prise-new']")
    WebElement firstItemPrice;

    @FindBy(xpath = "//div[@class = 'row-m']")
    WebElement priceFilterForm;
    @FindBy(xpath = "//input[@class = 'form-control form-control-min']")
    WebElement minPriceInputForm;
    @FindBy(xpath = "//input[@class = 'form-control form-control-max']")
    WebElement maxPriceInputForm;
    @FindBy(xpath = "//div[@class = 'form-group filter-group js_filter_parent open-filter-tooltip']//a[@class='filter-tooltip js_filters_accept']")
    WebElement showResultsButton;
    @FindBy(xpath = "//div[@class = 'prod-cart__prise-new']")
    List<WebElement> priceListWebElement;

    List<Integer> priceListInteger;
    List<Boolean> conformityListForRangeTest;

    private final static int MIN_LIMIT = 60650;
    private final static int MAX_LIMIT = 72600;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }


    public List<Boolean> getConformityListForRangeTest() {
        fillPriceFilterForm();
        setConformityListForRangeTest();
        return conformityListForRangeTest;
    }

    public List<Integer> getPriceListInteger() {
        return priceListInteger;
    }


    public List<WebElement> getSearchResultsList() {
        explicitWaitVisibilityOf(searchResultsProductsList.get(0), 10);
        return searchResultsProductsList;
    }

    public int getSearchResultsCount() {
        return getSearchResultsList().size();
    }

    public String getFirstItemPrice() {
        explicitWaitVisibilityOf(firstItemPrice, 10);
        return firstItemPrice.getText();
    }

    public void fillPriceFilterForm() {
        explicitWaitVisibilityOf(priceFilterForm, 10);//PRECONDITION

        new Actions(driver).doubleClick(minPriceInputForm).sendKeys(minPriceInputForm, String.valueOf(MIN_LIMIT)).build().perform();
        new Actions(driver).doubleClick(maxPriceInputForm).sendKeys(maxPriceInputForm, String.valueOf(MAX_LIMIT)).build().perform();

        explicitWaitVisibilityOf(showResultsButton, 15);
        showResultsButton.click();

        explicitWaitVisibilityOf(priceListWebElement.get(0), 10);
        priceListInteger = mapWebElementPriceListToIntegerPriceList(priceListWebElement);

    }

    public void setConformityListForRangeTest() {
        conformityListForRangeTest = new ArrayList<>();

        for (int price : priceListInteger) {
            System.out.println(priceListInteger);
            if (price > MIN_LIMIT && price < MAX_LIMIT)
                conformityListForRangeTest.add(true);
            else conformityListForRangeTest.add(false);
        }
    }
}
