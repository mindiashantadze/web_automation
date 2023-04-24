package ebay.carina.components.common;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;

public abstract class FilterBase extends AbstractUIObject {
    public FilterBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
    public abstract void typeMinPrice(BigDecimal minPrice);
    public abstract void typeMaxPrice(BigDecimal maxPrice);
    public abstract void submitPriceRange();
}
