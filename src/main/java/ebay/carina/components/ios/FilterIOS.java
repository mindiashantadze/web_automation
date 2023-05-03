package ebay.carina.components.ios;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import ebay.carina.components.common.FilterBase;
import ebay.carina.utils.locatorenums.FilterOptions;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;

public class FilterIOS extends FilterBase {
    @FindBy(xpath = "//span[@class = 'srp-multi-aspect__flyout__btn-label' and text() = '%s']")
    private ExtendedWebElement filterOption;

    @FindBy(xpath = "//input[@aria-label='Minimum Value']")
    private ExtendedWebElement inptMinPrice;

    @FindBy(xpath = "//input[@aria-label='Maximum Value']")
    private ExtendedWebElement inptMaxPrice;

    @FindBy(className = "srp-multi-aspect__price-range__submit")
    private ExtendedWebElement submitPriceRange;

    public FilterIOS(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    private void selectFilter(FilterOptions filterOption) {
        this.filterOption.format(filterOption.getFilterOptions()).click();
    }

    @Override
    public void typeMinPrice(BigDecimal minPrice) {
        inptMinPrice.type(minPrice.toString());
    }

    @Override
    public void typeMaxPrice(BigDecimal maxPrice) {
        inptMaxPrice.type(maxPrice.toString());
    }

    @Override
    public void submitPriceRange() {
        submitPriceRange.click();
    }

    @Override
    public void clickFilterBtn() {
        selectFilter(FilterOptions.PRICE);
    }
}
