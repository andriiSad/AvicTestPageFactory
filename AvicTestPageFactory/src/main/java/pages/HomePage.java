package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends pages.BasePage {
    @FindBy(xpath = "//input[@id = 'input_search']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@class = 'button-reset search-btn']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class= 'partner-box height']")
    private List<WebElement> partnersList;

    @FindBy(xpath = "//div[@class ='prod-cart__prise-old']")
    List<WebElement> itemsOnSaleOldWebElementPriceList;

    @FindBy(xpath = "//div[@class ='prod-cart__prise-new']")
    List<WebElement> itemsOnSaleNewWebElementPriceList;

    @FindBy(xpath = "//div[@id = 'js_popUp']//button")
    private WebElement closePopUpAddButton;


    @FindBy(xpath = "//li[@class = 'parent js_sidebar-item']//span[text() = 'Apple Store']")
    private WebElement appleStorePageButton;

    List<Boolean> conformityListForOldNew;

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public List<WebElement> getPartnersList() {
        return partnersList;
    }

    public List<WebElement> getItemsOnSaleOldWebElementPriceList() {
        return itemsOnSaleOldWebElementPriceList;
    }

    public List<WebElement> getItemsOnSaleNewWebElementPriceList() {
        return itemsOnSaleNewWebElementPriceList;
    }


    public int getPartnersListCount() {
        return getPartnersList().size();
    }


    public int getItemsOnSaleNewIntegerPriceListCount() {
        return mapWebElementPriceListToIntegerPriceList(getItemsOnSaleNewWebElementPriceList()).size();
    }

    public int getItemsOnSaleOldIntegerPriceListCount() {
        return mapWebElementPriceListToIntegerPriceList(getItemsOnSaleOldWebElementPriceList()).size();
    }

    public List<Boolean> getConformityListForOldNew() {
        setConformityListForOldNew();
        return conformityListForOldNew;
    }

    public void searchByKeyWord(final String keyWord) {
        searchInput.sendKeys(keyWord);
        searchButton.click();
    }

    public void setConformityListForOldNew() {
        conformityListForOldNew = new ArrayList<>();

        if (getItemsOnSaleOldIntegerPriceListCount() != getItemsOnSaleNewIntegerPriceListCount())
            throw new IllegalArgumentException();
        for (int i = 0; i < getItemsOnSaleOldIntegerPriceListCount(); i++) {
            if (mapWebElementPriceListToIntegerPriceList(getItemsOnSaleNewWebElementPriceList()).get(i)
                    <
                    mapWebElementPriceListToIntegerPriceList(getItemsOnSaleOldWebElementPriceList()).get(i))
                conformityListForOldNew.add(true);
            else conformityListForOldNew.add(false);
        }
    }


    public void closePopUpAdd() {
        explicitWaitVisibilityOf(closePopUpAddButton, 15);
        closePopUpAddButton.click();
    }

    public void navigateToAppleStorePage() {
        appleStorePageButton.click();
    }


}
