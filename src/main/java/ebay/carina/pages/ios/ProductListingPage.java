package ebay.carina.pages.ios;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.locator.Context;
import ebay.carina.components.common.FilterBase;
import ebay.carina.components.desktop.FilterDesktop;
import ebay.carina.pages.common.LoginPageBase;
import ebay.carina.pages.common.ProductListingPageBase;
import ebay.carina.utils.locatorenums.SortOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.math.BigDecimal;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductListingPageBase.class)
public class ProductListingPage extends ProductListingPageBase {
    private final static Logger LOGGER = LoggerFactory.getLogger(ebay.carina.pages.desktop.ProductListingPage.class);

    @FindBy(id = "srp-river-results")
    private ExtendedWebElement divResults;

    @Context(dependsOn = "divResults")
    @FindBy(className = "s-item__title")
    private List<ExtendedWebElement> productNameLbls;

    @Context(dependsOn = "divResults")
    @FindBy(className = "s-item__details")
    private List<ExtendedWebElement> divProductDetails;

    @FindBy(className = "srp-save-null-search__heading")
    private ExtendedWebElement noProductFoundLbl;

    @FindBy(xpath = "//div[@id='srp-river-results']//span[@class='s-item__price']")
    private List<ExtendedWebElement> priceLbls;

    @FindBy(id = "s0-51-16-0-1-2-6")
    private FilterDesktop filterDesktop;

    @FindBy(xpath = "//span[text() = '%s']/span")
    private ExtendedWebElement activeCategory;

    @FindBy(xpath = "//button[text() = 'Save this search']")
    private ExtendedWebElement btnSaveSearch;

    @FindBy(className = "srp-sort")
    private ExtendedWebElement btnSortOptions;

    @Context(dependsOn = "btnSortOptions")
    @FindBy(xpath = "//a//span[text() = '%s']")
    private ExtendedWebElement btnSortOption;

    @FindBy(xpath = "//div[@id = 's0-51-16-5-4[0]']//span[text()='%s']")
    private ExtendedWebElement filterButton;

    @FindBy(xpath = "//ul[@class = 'fake-menu__items']//span[text() = '%s']")
    private ExtendedWebElement filterOption;


    @FindBy(id = "gh-cat")
    private ExtendedWebElement categoryOptions;

    @Context(dependsOn = "categoryOptions")
    @FindBy(xpath = "//option[normalize-space() = '%s']")
    private ExtendedWebElement categoryOption;

    @FindBy(className = "s-progressive-spinner")
    private ExtendedWebElement loader;

    public ProductListingPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(divResults);
    }

    @Override
    public String getNoProductFoundLbl() {
        return null;
    }

    @Override
    public boolean isCategoryActive(String category) {
        return false;
    }

    @Override
    public List<BigDecimal> getProductPrices() {
        return null;
    }

    @Override
    public List<BigDecimal> getProductPricesWithShipping() {
        return null;
    }

    @Override
    public void validateProductName(String productName) {
        for (ExtendedWebElement productNameLbl : productNameLbls) {
            LOGGER.info("Product name: " + productNameLbl.getText());
            String productNameText = productNameLbl.getText().toLowerCase().trim();
            Assert.assertTrue(productNameText.contains(productName.toLowerCase()), "Expected product to contain " + productName + ", but the result was " + productNameLbl.getText());
        }
    }

    @Override
    public void validateProductName(String productName, String excludedWords) {
        for (ExtendedWebElement productNameLbl : productNameLbls) {
            LOGGER.info(productNameLbl.getText());
            String productNameText = productNameLbl.getText().toLowerCase().trim();
            Assert.assertTrue(productNameText.contains(productName.toLowerCase()), "Expected product to contain " + productName + ", but the result was " + productNameLbl.getText());
        }
    }

    @Override
    public LoginPageBase clickSaveSearch() {
        return null;
    }

    @Override
    public void selectCategory(String category) {

    }

    @Override
    public void validateFreeShipping() {

    }

    @Override
    public void selectFilter(String filterName) {

    }

    @Override
    public void selectOption(String option) {

    }

    @Override
    public FilterBase getFilter() {
        return null;
    }

    @Override
    public void selectSortingOption(SortOptions sortOption) {

    }
}
