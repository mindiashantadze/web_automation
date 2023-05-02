package ebay.carina.pages.ios;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.annotations.Predicate;
import ebay.carina.pages.common.NativePageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = NativePageBase.class)
public class NativePage extends NativePageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(NativePage.class);

    @Predicate
    @FindBy(xpath = "label == 'Tabs'")
    private ExtendedWebElement btnTabs;

    @Predicate
    @FindBy(xpath = "name == 'TabOverviewItemView'")
    private ExtendedWebElement btnTabOverview;

    public NativePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void clickMenu() {
        btnTabs.click();
    }

    @Override
    public void clickNewTab() {

    }

    @Override
    public void clickTabSwitcher() {

    }

    @Override
    public void clickCloseTab() {

    }

    @Override
    public void clickTabThumbnail() {

    }
}
