package ebay.carina.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import ebay.carina.utils.locatorenums.KeyWordOptions;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;

public abstract class AdvancedSearchPageBase extends AbstractPage {
    protected AdvancedSearchPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void typeIncludedWords(String words);
    public abstract void typeExcludedWords(String words);
    public abstract void submitFilter();
    public abstract void selectKeyWordOptions(KeyWordOptions keyWordOptions);
    public abstract void enterMinPrice(BigDecimal price);
    public abstract void enterMaxPrice(BigDecimal price);
}
