package ebay.carina.pages.android;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import ebay.carina.pages.common.NativePageBase;
import ebay.carina.pages.common.ProductListingPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = NativePageBase.class)
public class NativePage extends NativePageBase {
    @FindBy(id = "com.android.chrome:id/menu_button")
    private ExtendedWebElement btnMenu;

    @FindBy(xpath = "//*[@content-desc = 'New tab']")
    private ExtendedWebElement btnNewTab;

    @FindBy(id = "com.android.chrome:id/tab_switcher_button")
    private ExtendedWebElement btnTabSwitcher;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc='Close New tab tab']")
    private ExtendedWebElement btnCloseTab;

    @FindBy(xpath = "//*[@resource-id = 'com.android.chrome:id/tab_thumbnail']")
    private ExtendedWebElement btnTabThumbnail;

    public NativePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void clickMenu() {
        btnMenu.click();
    }

    @Override
    public void clickNewTab() {
        btnNewTab.click();
    }

    @Override
    public void clickTabSwitcher() {
        btnTabSwitcher.click();
    }

    @Override
    public void clickCloseTab() {
        btnCloseTab.click();
    }

    @Override
    public void clickTabThumbnail() {
        btnTabThumbnail.click();
    }
}
