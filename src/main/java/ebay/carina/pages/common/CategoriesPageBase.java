package ebay.carina.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class CategoriesPageBase extends AbstractPage {
    protected CategoriesPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract String getCategoriesTitle();
}
