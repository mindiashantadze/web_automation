package ebay.carina.pages.common;

import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import ebay.carina.components.common.FilterBase;
import ebay.carina.utils.locatorenums.FilterOptions;
import ebay.carina.utils.locatorenums.SortOptions;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;
import java.util.List;

public abstract class ProductListingPageBase extends AbstractPage {
    public ProductListingPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract String getNoProductFoundLbl();
    public abstract boolean isCategoryActive(String category);
    public abstract List<BigDecimal> getProductPrices();
    public abstract List<BigDecimal> getProductPricesWithShipping();
    public abstract void validateProductName(String productName);
    public abstract void validateProductName(String productName, String excludedWords);
    public abstract LoginPageBase clickSaveSearch();
    public abstract void selectCategory(String category);
    public abstract void validateFreeShipping();
    public abstract void selectFreeShippingOption();
    public abstract void selectOption(FilterOptions option);
    public abstract FilterBase getFilter();
    public abstract void selectSortingOption(SortOptions sortOption);
}
