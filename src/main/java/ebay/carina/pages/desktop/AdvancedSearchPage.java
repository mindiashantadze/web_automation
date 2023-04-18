package ebay.carina.pages.desktop;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import ebay.carina.locatorenums.KeyWordOptions;
import ebay.carina.pages.common.AdvancedSearchPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;


@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = AdvancedSearchPageBase.class)
public class AdvancedSearchPage extends AdvancedSearchPageBase {
    @FindBy(id = "_nkw")
    ExtendedWebElement inptIncludedWords;

    @FindBy(id = "_ex_kw")
    ExtendedWebElement inptExcludedWords;

    @FindBy(xpath = "//button[@type='submit']")
    ExtendedWebElement submitBtn;

    @FindBy(xpath = "//select[@name = '_in_kw']")
    ExtendedWebElement selectKeywordOptions;

    @FindBy(xpath = "//select[@name = '_in_kw']/option[text() = '%s']")
    ExtendedWebElement optionExactWordAndOrder;

    @FindBy(xpath = "//input[@name='_udlo']")
    ExtendedWebElement inptMinPrice;

    @FindBy(xpath = "//input[@name='_udhi']")
    ExtendedWebElement inptMaxPrice;


    public AdvancedSearchPage(WebDriver driver) {
        super(driver);
    }

    public void typeIncludedWords(String words) {
        this.inptIncludedWords.type(words);
    }

    public void typeExcludedWords(String words) {
        this.inptExcludedWords.type(words);
    }

    public void submitFilter() {
        this.submitBtn.click();
    }

    public void selectKeyWordOptions(KeyWordOptions keyWordOptions) {
        this.selectKeywordOptions.click();
        ExtendedWebElement keyWordOption = findExtendedWebElement(By.xpath(String.format("//select[@name = '_in_kw']/option[text() = '%s']", keyWordOptions.getKeyWordOptions())));
        keyWordOption.click();
    }

    public void enterMinPrice(BigDecimal price) {
        this.inptMinPrice.type(price.toString());
    }

    public void enterMaxPrice(BigDecimal price) {
        this.inptMaxPrice.type(price.toString());
    }
}
