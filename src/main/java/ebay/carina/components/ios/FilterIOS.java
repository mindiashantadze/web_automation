package ebay.carina.components.ios;

import ebay.carina.components.common.FilterBase;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;

public class FilterIOS extends FilterBase {
    public FilterIOS(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public void typeMinPrice(BigDecimal minPrice) {

    }

    @Override
    public void typeMaxPrice(BigDecimal maxPrice) {

    }

    @Override
    public void submitPriceRange() {

    }

    @Override
    public void clickFilterBtn() {

    }
}
