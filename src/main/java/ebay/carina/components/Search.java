package ebay.carina.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import ebay.carina.pages.AdvancedSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Search extends AbstractUIObject {
    @FindBy(id = "gh-ac")
    ExtendedWebElement inptSearchField;

    @FindBy(id = "gh-btn")
    ExtendedWebElement btnSearchProducts;

    @FindBy(id = "gh-cat-box")
    ExtendedWebElement categoriesDropDown;

    @FindBy(id = "gh-as-a")
    ExtendedWebElement advancedBtn;

    public Search(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void typeInSearchField(String productName) {
        inptSearchField.type(productName);
    }

    public void clickSearchButton() {
        btnSearchProducts.click();
    }

    public void selectCategory(String categoryName) {
        categoriesDropDown.click();
        ExtendedWebElement categoryOption = findExtendedWebElement(By.xpath(String.format("//select[@id = 'gh-cat']/option[text() = '%s']", categoryName)));
        categoryOption.click();
    }

    public AdvancedSearchPage goToAdvancedSearchPage() {
        advancedBtn.click();
        return new AdvancedSearchPage(driver);
    }
}
