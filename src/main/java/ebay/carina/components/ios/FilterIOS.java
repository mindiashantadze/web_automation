package ebay.carina.components.ios;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import ebay.carina.components.common.FilterBase;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;

public class FilterIOS extends FilterBase {
    @FindBy(xpath = "//span[@class = 'srp-multi-aspect__flyout__btn-label' and text() = '%s']")
    private ExtendedWebElement filterOption;

    @FindBy(id = "s0-17-23-6-3-4[0]-3-1-13-9[1]-0-0-2-flyout-7-5-4-9-10-textbox")
    private ExtendedWebElement inptMinPrice;

    @FindBy(id = "s0-17-23-6-3-4[0]-3-1-13-9[1]-0-0-2-flyout-7-5-4-9-13-textbox")
    private ExtendedWebElement inptMaxPrice;

    @FindBy(className = "srp-multi-aspect__price-range__submit")
    private ExtendedWebElement submitPriceRange;

    public FilterIOS(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    private void selectFilter(String filterOption) {
        this.filterOption.format(filterOption).click();
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
        selectFilter("Price");
    }
}
