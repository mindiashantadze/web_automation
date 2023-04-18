package ebay.carina.pages.android;

import com.zebrunner.carina.utils.factory.DeviceType;
import ebay.carina.pages.common.HomePageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ebay.carina.components.android.SearchMobile;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {
    @FindBy(tagName = "form")
    private SearchMobile searchSection;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SearchMobile getSearchSection() {
        return this.searchSection;
    }
}
