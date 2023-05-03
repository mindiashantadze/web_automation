package ebay.carina.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import ebay.carina.components.common.SearchBase;
import org.openqa.selenium.WebDriver;

public abstract class HomePageBase extends AbstractPage  {
    public HomePageBase(WebDriver driver) {
        super(driver);
    }

    public abstract SearchBase getSearchSection();
}
