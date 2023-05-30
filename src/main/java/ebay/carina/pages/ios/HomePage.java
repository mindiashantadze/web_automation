package ebay.carina.pages.ios;

import com.zebrunner.carina.utils.factory.DeviceType;
import ebay.carina.components.common.SearchBase;
import ebay.carina.components.ios.SearchIOS;
import ebay.carina.pages.common.HomePageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {
    @FindBy(id = "globalHeaderWrapper")
    private SearchIOS searchIos;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SearchBase getSearchSection() {
        return searchIos;
    }
}
