package ebay.carina.pages.ios;

import com.zebrunner.carina.utils.factory.DeviceType;
import ebay.carina.pages.common.CategoriesPageBase;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CategoriesPageBase.class)
public class CategoriesPage extends CategoriesPageBase {
    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getCategoriesTitle() {
        return null;
    }
}
