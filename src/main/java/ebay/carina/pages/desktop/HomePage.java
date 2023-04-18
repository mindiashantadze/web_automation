package ebay.carina.pages.desktop;

import com.zebrunner.carina.utils.factory.DeviceType;
import ebay.carina.pages.common.HomePageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ebay.carina.components.desktop.SearchDesktop;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase  {
    @FindBy(id = "gh-f")
    private SearchDesktop searchSection;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SearchDesktop getSearchSection() {
        return this.searchSection;
    }
}
