package ebay.carina.pages.ios;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import ebay.carina.pages.common.LoginPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase {
    @FindBy(id = "signin-reg-msg")
    ExtendedWebElement lblSignInMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getSignInMessage() {
        return lblSignInMessage.getText().trim();
    }
}
