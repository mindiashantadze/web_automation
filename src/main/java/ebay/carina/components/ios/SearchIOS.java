package ebay.carina.components.ios;

import com.zebrunner.carina.utils.factory.ICustomTypePageFactory;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import ebay.carina.components.common.SearchBase;
import ebay.carina.pages.common.ProductListingPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SearchIOS extends SearchBase implements ICustomTypePageFactory {
    @FindBy(className = "gh-search__input")
    private ExtendedWebElement inptSearchField;

    @FindBy(className = "gh-search__submitbtn")
    private ExtendedWebElement btnSearch;

    @FindBy(className = "gh-header-item__button gh__needs-js")
    private ExtendedWebElement btnNavigation;

    @FindBy(xpath = "//nav//span[text() = '%s']")
    private ExtendedWebElement btnCategory;

    public SearchIOS(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public void typeInSearchField(String productName) {
        inptSearchField.type(productName);
    }

    @Override
    public void clickSearchButton() {
        btnSearch.click();
    }

    @Override
    public void selectCategory(String categoryName) {
        btnNavigation.click();
        btnCategory.format(categoryName).clickByJs();
    }
}
