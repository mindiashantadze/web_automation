package ebay.carina.components.desktop;

import com.zebrunner.carina.utils.factory.ICustomTypePageFactory;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import ebay.carina.components.common.SearchBase;
import ebay.carina.pages.common.ProductListingPageBase;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SearchDesktop extends SearchBase implements ICustomTypePageFactory {
    @FindBy(id = "gh-ac")
    ExtendedWebElement inptSearchField;

    @FindBy(id = "gh-btn")
    ExtendedWebElement btnSearchProducts;

    @FindBy(id = "gh-cat-box")
    ExtendedWebElement categoriesDropDown;

    @FindBy(id = "gh-as-a")
    ExtendedWebElement advancedBtn;

    @FindBy(xpath = "//select[@id = 'gh-cat']/option[text() = '%s']")
    ExtendedWebElement categoryOption;

    public SearchDesktop(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public void typeInSearchField(String productName) {
        inptSearchField.type(productName);
    }

    @Override
    public ProductListingPageBase clickSearchButton() {
        btnSearchProducts.click();
        return initPage(driver, ProductListingPageBase.class);
    }

    @Override
    public void selectCategory(String categoryName) {
        categoriesDropDown.click();
        categoryOption.format(categoryName).click();
    }
}
