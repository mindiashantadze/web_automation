package ebay.carina.components.common;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import ebay.carina.pages.common.ProductListingPageBase;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class SearchBase extends AbstractUIObject {
    public SearchBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract void typeInSearchField(String productName);
    public abstract void clickSearchButton();
    public abstract void selectCategory(String categoryName);
}
