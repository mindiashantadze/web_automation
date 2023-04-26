package ebay.carina.pages.ios;

import ebay.carina.components.common.FilterBase;
import ebay.carina.pages.common.LoginPageBase;
import ebay.carina.pages.common.ProductListingPageBase;
import ebay.carina.utils.locatorenums.SortOptions;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;
import java.util.List;

public class ProductListingPage extends ProductListingPageBase {
    protected ProductListingPage(WebDriver driver) {
        super(driver);
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

    }

    @Override
    public void validateProductName(String productName, String excludedWords) {

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
