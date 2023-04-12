package ebay.carina.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CategoriesPage extends AbstractPage {
    @FindBy(tagName = "h1")
    private ExtendedWebElement categoriesTitle;

    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    public String getCategoriesTitle() {
        return this.categoriesTitle.getText().trim();
    }
}
