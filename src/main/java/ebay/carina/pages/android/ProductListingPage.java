package ebay.carina.pages.android;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import ebay.carina.pages.common.ProductListingPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductListingPageBase.class)
public class ProductListingPage extends ProductListingPageBase {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProductListingPage.class);

    @FindBy(xpath = "//div[@id = 'srp-river-results']//span[@role = 'heading']")
    private List<ExtendedWebElement> productNameLbls;

    @FindBy(className = "srp-save-null-search__heading")
    private ExtendedWebElement noProductFoundLbl;

    @FindBy(xpath = "//div[@id='srp-river-results']//span[@class='s-item__price']")
    private List<ExtendedWebElement> priceLbls;

    public ProductListingPage(WebDriver driver) {
        super(driver);
    }

    public void validateProductName(String productName) {
        for (ExtendedWebElement productNameLbl : productNameLbls) {
            LOGGER.info(productNameLbl.getText());
            String productNameText = productNameLbl.getText().toLowerCase().trim();
            Assert.assertTrue(productNameText.contains(productName.toLowerCase()), "Expected product to contain " + productName + ", but the result was " + productNameLbl.getText());
        }
    }

    public void validateProductName(String productName, String excludedWords) {
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
        ExtendedWebElement activeCategory = findExtendedWebElement(By.xpath(String.format("//span[text() = '%s']/span", category)));
        return activeCategory.getText().contains("Selected category");
    }

    public List<BigDecimal> getProductPrices() {
        List<BigDecimal> prices = new LinkedList<>();
        for (ExtendedWebElement priceLbl : priceLbls) {
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
}
