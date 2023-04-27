package ebay.carina.pages.ios;

import com.zebrunner.carina.utils.factory.DeviceType;
import ebay.carina.pages.common.LoginPageBase;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getSignInMessage() {
        return null;
    }
}
