package ebay.carina.pages.desktop;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import ebay.carina.pages.common.CategoriesPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = CategoriesPageBase.class)
public class CategoriesPage extends CategoriesPageBase {
    @FindBy(tagName = "h1")
    private ExtendedWebElement categoriesTitle;

    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    public String getCategoriesTitle() {
        return this.categoriesTitle.getText().trim();
    }
}
