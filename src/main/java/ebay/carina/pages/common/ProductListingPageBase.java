package ebay.carina.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;
import java.util.List;

public abstract class ProductListingPageBase extends AbstractPage {
    protected ProductListingPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract String getNoProductFoundLbl();
    public abstract boolean isCategoryActive(String category);
    public abstract List<BigDecimal> getProductPrices();
    public abstract void validateProductName(String productName);
    public abstract void validateProductName(String productName, String excludedWords);
}
