package ebay.carina.pages.ios;

import ebay.carina.pages.common.CategoriesPageBase;
import org.openqa.selenium.WebDriver;

public class CategoriesPage extends CategoriesPageBase {
    protected CategoriesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getCategoriesTitle() {
        return null;
    }
}
