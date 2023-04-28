package ebay.carina.pages.ios;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.Context;
import ebay.carina.components.common.FilterBase;
import ebay.carina.components.ios.FilterIOS;
import ebay.carina.pages.common.LoginPageBase;
import ebay.carina.pages.common.ProductListingPageBase;
import ebay.carina.utils.locatorenums.SortOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.math.BigDecimal;
import java.util.LinkedList;
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

    @FindBy(xpath = "//span[text() = '%s']/span")
    private ExtendedWebElement activeCategory;

    @FindBy(xpath = "//span[text() = 'Save this search']")
    private ExtendedWebElement btnSaveSearch;

    @FindBy(xpath = "//button[contains(@class, 'srp-controls__control--link-enabled btn') and contains(text(), 'Sort')]")
    private ExtendedWebElement btnSortOptions;

    @FindBy(xpath = "//span[@class = 's-sort-label' and text() = '%s']")
    private ExtendedWebElement btnSortOption;

    @FindBy(xpath = "(//button[text() = 'Filter'])[1]")
    private ExtendedWebElement filterButton;

    @FindBy(xpath = "//span[text() = '%s']")
    private ExtendedWebElement filterOption;

    @FindBy(xpath = "//ul[@id = 'c4-filter-spoke-1-2']//span[text() = 'Music']")
    private ExtendedWebElement categoryOption;

    @FindBy(xpath = "//div[@class = 'filter__submit']//button")
    private ExtendedWebElement showResultsBtn;

    @FindBy(className = "srp-multi-aspect-guidance--hide-separators")
    private FilterIOS filter;

    public ProductListingPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(divResults);
        // uiloadedmarker didn't work fos IOS
        pause(3);
    }

    @Override
    public String getNoProductFoundLbl() {
        return noProductFoundLbl.getText().trim();
    }

    @Override
    public boolean isCategoryActive(String category) {
        filterButton.click();
        filterOption.format("Category").click();
        LOGGER.info(categoryOption.format(category).getAttribute("class"));
        return categoryOption.format(category).getAttribute("class").contains("BOLD");
    }

    @Override
    public List<BigDecimal> getProductPrices() {
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

    @Override
    public List<BigDecimal> getProductPricesWithShipping() {
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
        btnSaveSearch.click();
        return initPage(driver, LoginPageBase.class);
    }

    @Override
    public void selectCategory(String category) {
        filterButton.click();
        filterOption.format("Category").click();
        categoryOption.format(category).click();
        pause(3);
        showResultsBtn.click();
    }

    @Override
    public void validateFreeShipping() {
        for (ExtendedWebElement detailsDiv : divProductDetails) {
            ExtendedWebElement priceLbl = detailsDiv.findExtendedWebElement(By.className("s-item__shipping"));
            LOGGER.info("Shipping price: " + priceLbl.getText());
            Assert.assertEquals(priceLbl.getText().trim(), "Free International Shipping");
        }
    }

    @Override
    public void selectFreeShippingOption() {
        filterButton.click();
        filterOption.format("Shipping Options").click();
    }

    @Override
    public void selectOption(String option) {
        filterOption.format(option).click();
    }

    @Override
    public FilterBase getFilter() {
        return filter;
    }

    @Override
    public void selectSortingOption(SortOptions sortOption) {
        btnSortOptions.click();
        btnSortOption.format(sortOption.getSortOption()).click();
    }
}
