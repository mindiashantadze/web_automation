package ebay.carina.pages.ios;

import ebay.carina.pages.common.LoginPageBase;
import org.openqa.selenium.WebDriver;

public class LoginPage extends LoginPageBase {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getSignInMessage() {
        return null;
    }
}
