package ebay.carina.pages.android;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.Context;
import ebay.carina.components.android.FilterMobile;
import ebay.carina.components.common.FilterBase;
import ebay.carina.utils.locatorenums.CategoryOption;
import ebay.carina.utils.locatorenums.FilterOptions;
import ebay.carina.utils.locatorenums.SortOptions;
import ebay.carina.pages.common.LoginPageBase;
import ebay.carina.pages.common.ProductListingPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductListingPageBase.class)
public class ProductListingPage extends ProductListingPageBase {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProductListingPage.class);

    @FindBy(xpath = "//ul[contains(@class, 'srp-results')]")
    private ExtendedWebElement divResults;

    @Context(dependsOn = "divResults")
    @FindBy(className = "s-item__details")
    private List<ExtendedWebElement> divProductDetails;

    @Context(dependsOn = "divResults")
    @FindBy(xpath = "//ul[contains(@class, 'srp-re')]//span[@role = 'heading']")
    private List<ExtendedWebElement> productNameLbls;

    @FindBy(className = "srp-save-null-search__heading")
    private ExtendedWebElement noProductFoundLbl;

    @FindBy(xpath = "//span[text() = '%s']/span")
    private ExtendedWebElement activeCategory;

    @FindBy(xpath = "//button[text() = 'Save this search']")
    private ExtendedWebElement btnSaveSearch;

    @FindBy(id = "s0-51-16-0-1-2-6")
    private FilterMobile filterMobile;

    @FindBy(className = "srp-controls__refine")
    private ExtendedWebElement btnFilter;

    @FindBy(className = "x-refine-expander")
    private ExtendedWebElement btnCategories;

    @FindBy(xpath = "//ul[@id = 's0-51-16-0-1-2-6']//span[text() = '%s']")
    ExtendedWebElement categoryOption;

    @FindBy(xpath = "//span[contains(@class, 'x-refine__multi-select-cbx') and text() = '%s']")
    private ExtendedWebElement filterOption;

    @FindBy(className = "srp-sort")
    private ExtendedWebElement btnSortOptions;

    @Context(dependsOn = "btnSortOptions")
    @FindBy(xpath = "//a//span[text() = '%s']")
    private ExtendedWebElement btnSortOption;

    public ProductListingPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(divResults);
    }

    private void waitForProducts() {
        waitUntil(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("s-item__details")), 10);
    }

    public void validateProductName(String productName) {
        waitForProducts();
        for (ExtendedWebElement productNameLbl : productNameLbls) {
            LOGGER.info(productNameLbl.getText());
            String productNameText = productNameLbl.getText().toLowerCase().trim();
            Assert.assertTrue(productNameText.contains(productName.toLowerCase()), "Expected product to contain " + productName + ", but the result was " + productNameLbl.getText());
        }
    }

    public void validateProductName(String productName, String excludedWords) {
        waitForProducts();
        for (ExtendedWebElement productNameLbl : productNameLbls) {
            LOGGER.info(productNameLbl.getText());
            String productNameText = productNameLbl.getText().toLowerCase().trim();
            Assert.assertTrue(
                    productNameText.contains(productName.toLowerCase()) && productNameText.contains(excludedWords),
                    "Expected product to contain " + productName + "and not contain " + excludedWords + ", but the result was " + productNameLbl.getText()
            );
        }
    }

    public String getNoProductFoundLbl() {
        return this.noProductFoundLbl.getText().trim();
    }

    public boolean isCategoryActive(String category) {
        btnCategories.click();
        return activeCategory.format(category).getText().contains("Selected category");
    }

    public List<BigDecimal> getProductPrices() {
        waitForProducts();
        List<BigDecimal> prices = new LinkedList<>();
        for (ExtendedWebElement detailsDiv : divProductDetails) {
            ExtendedWebElement priceLbl = detailsDiv.findExtendedWebElement(By.className("s-item__price"));
            LOGGER.info("Price:" + priceLbl.getText());
            String price = priceLbl.getText().trim();

            if (price.toLowerCase().contains("to")) {
                price = price.split(" to ")[0];
            }

            price = price.replace("$", "");
            prices.add(new BigDecimal(price));
        }

        return prices;
    }

    public List<BigDecimal> getProductPricesWithShipping() {
        waitForProducts();
        List<BigDecimal> prices = new LinkedList<>();
        for (ExtendedWebElement detailsDiv : divProductDetails) {
            ExtendedWebElement priceLbl = detailsDiv.findExtendedWebElement(By.className("s-item__price"));
            ExtendedWebElement shippingPriceLbl = detailsDiv.findExtendedWebElement(By.className("s-item__shipping"));

            LOGGER.info("Price:" + priceLbl.getText());
            LOGGER.info("Shipping price:" + shippingPriceLbl.getText());

            String price = priceLbl.getText().trim();
            String shippingPrice = shippingPriceLbl.getText().trim();

            if (shippingPrice.equals("Shipping not specified")) {
                continue;
            }

            if (shippingPrice.equals("Free International Shipping")) {
                shippingPrice = "0.00";
            }

            if (price.toLowerCase().contains("to")) {
                price = price.split(" to ")[0];
            }

            shippingPrice = shippingPrice.replaceAll("[^\\d.]", "");

            price = price.replaceAll("[^\\d.]", "");

            prices.add(new BigDecimal(price).add(new BigDecimal(shippingPrice)));
        }

        return prices;
    }

    public LoginPageBase clickSaveSearch() {
        btnSaveSearch.click();
        return initPage(driver, LoginPageBase.class);
    }

    @Override
    public void validateFreeShipping() {
        waitForProducts();
        for (ExtendedWebElement detailsDiv : divProductDetails) {
            ExtendedWebElement priceLbl = detailsDiv.findExtendedWebElement(By.className("s-item__shipping"));
            LOGGER.info("Shipping price: " + priceLbl.getText());
            Assert.assertEquals(priceLbl.getText().trim(), "Free International Shipping");
        }
    }

    @Override
    public void selectFreeShippingOption() {
        LOGGER.info("Step is not required for android phones");
    }

    @Override
    public void selectOption(FilterOptions option) {
        filterOption.format(option.getFilterOptions()).clickByJs();
    }

    @Override
    public FilterBase getFilter() {
        return filterMobile;
    }

    @Override
    public void selectCategory(CategoryOption category) {
        btnCategories.click();
        categoryOption.format(CategoryOption.SHOW_MORE.getCategory()).clickByJs();
        categoryOption.format(category.getCategory()).clickByJs();
    }

    @Override
    public void selectSortingOption(SortOptions sortOption) {
        btnSortOptions.click();
        btnSortOption.format(sortOption.getSortOption()).clickByJs();
    }
}
