package ebay.carina.components.desktop;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.Context;
import ebay.carina.components.common.FilterBase;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;

public class FilterDesktop extends FilterBase {
    @FindBy(xpath = "//button[@title='Submit price range']")
    private ExtendedWebElement btnShowResults;

    @FindBy(xpath = "(//div[@class = 'price-range'])[1]//input")
    private ExtendedWebElement inptMinPrice;

    @FindBy(xpath = "(//div[@class = 'price-range'])[2]//input")
    private ExtendedWebElement inptMaxPrice;

    @FindBy(className = "x-textrange__button")
    private ExtendedWebElement btnSubmitPriceRange;

    public FilterDesktop(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public void typeMinPrice(BigDecimal minPrice) {
        inptMinPrice.scrollTo();
        inptMinPrice.type(minPrice.toString());
    }

    @Override
    public void typeMaxPrice(BigDecimal maxPrice) {
        inptMaxPrice.type(maxPrice.toString());
    }

    @Override
    public void submitPriceRange() {
        btnSubmitPriceRange.click();
    }

    @Override
    public void clickFilterBtn() {

    }
}
