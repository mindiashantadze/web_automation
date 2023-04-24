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

    @FindBy(xpath = "(//div[@class = 'price-range'])[1]//input")
    private ExtendedWebElement inptMinPrice;

    @FindBy(xpath = "(//div[@class = 'price-range'])[2]//input")
    private ExtendedWebElement inptMaxPrice;

    @FindBy(xpath ="//div[@class = 'srp-controls__default-refinements  clearfix']//button[@class = 'icon-btn']")
    private ExtendedWebElement btnFilter;

    public FilterMobile(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    private void selectFilter(String filter) {
        filterOption.format(filter);
    }

    @Override
    public void typeMinPrice(BigDecimal minPrice) {
        inptMinPrice.type(minPrice.toString());
    }

    @Override
    public void typeMaxPrice(BigDecimal maxPrice) {
        inptMaxPrice.type(maxPrice.toString());
    }

    @FindBy(className = "x-textrange__button")
    private ExtendedWebElement btnSubmitPriceRange;

    @Override
    public void submitPriceRange() {

    }

    @Override
    public void clickFilterBtn() {
        btnFilter.click();
    }
}
