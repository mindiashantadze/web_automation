package ebay.carina.components.android;

import com.zebrunner.carina.utils.factory.ICustomTypePageFactory;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import ebay.carina.components.common.SearchBase;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SearchMobile extends SearchBase implements ICustomTypePageFactory {
    @FindBy(xpath = "//input[@name='_nkw']")
    ExtendedWebElement inptSearchField;

    @FindBy(className = "gh-search__submitbtn")
    ExtendedWebElement btnSearchProducts;

    @FindBy(id = "gh-cat-box")
    ExtendedWebElement categoriesDropDown;

    @FindBy(id = "gh-as-a")
    ExtendedWebElement advancedBtn;

    @FindBy(xpath = "//select[@id = 'gh-cat']/option[text() = '%s']")
    ExtendedWebElement categoryOption;

    public SearchMobile(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
        setUiLoadedMarker(inptSearchField);
    }

    @Override
    public void typeInSearchField(String productName) {
        inptSearchField.type(productName);
    }

    @Override
    public void clickSearchButton() {
        btnSearchProducts.click();
    }

    @Override
    public void selectCategory(String categoryName) {
        categoriesDropDown.click();
        categoryOption.format(categoryName).click();
    }
}
