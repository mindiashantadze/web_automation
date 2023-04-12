package ebay.carina.pages;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ebay.carina.components.Search;

public class HomePage extends AbstractPage  {
    @FindBy(id = "gh-f")
    private Search searchSection;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public Search getSearchSection() {
        return this.searchSection;
    }
}
