package ebay.carina.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class NativePageBase extends AbstractPage {
    public NativePageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void clickMenu();
    public abstract void clickNewTab();
    public abstract void clickTabSwitcher();
    public abstract void clickCloseTab();
    public abstract void clickTabThumbnail();
}
