package ebay.carina.components.android;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.Context;
import ebay.carina.components.common.FilterBase;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;

public class FilterMobile extends FilterBase {
    @FindBy(className = "filter__hub__content")
    private ExtendedWebElement filterOptions;

    @Context(dependsOn = "filterOptions")
    @FindBy(xpath = "//span[@class = 'filter__label' and text() = '%s']")
    ExtendedWebElement filterOption;

    @FindBy(xpath = "//div[@id = 'c4-filter-spoke']//span[text() = '%s']")
    ExtendedWebElement categoryOption;

    @FindBy(id = "c4-filter-spoke-1-7-8-textbox")
    ExtendedWebElement inptMinPrice;

    @FindBy(id = "c4-filter-spoke-1-7-10-textbox")
    ExtendedWebElement inptMaxPrice;

    @FindBy(id = "9")
    ExtendedWebElement btnShowResults;

    public FilterMobile(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    private void selectFilter(String filter) {
        filterOption.format(filter);
    }

    @Override
    public void selectCategory(String category) {
        categoryOption.format(category).click();
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

    }

    private void submitFilter() {
        btnShowResults.click();
    }
}
