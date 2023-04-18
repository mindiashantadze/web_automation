package ebay.carina;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.zebrunner.agent.core.annotation.TestLabel;
import com.zebrunner.carina.utils.factory.ICustomTypePageFactory;
import ebay.carina.locatorenums.KeyWordOptions;
import ebay.carina.pages.common.AdvancedSearchPageBase;
import ebay.carina.pages.common.HomePageBase;
import ebay.carina.pages.common.CategoriesPageBase;
import ebay.carina.pages.common.ProductListingPageBase;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

public class SearchTest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchTest.class);
    private final static String CATEGORIES_TITLE = "All Categories";
    private final static String PRODUCTS_NOT_FOUND_MSG = "No exact matches found";

    @Test(groups = {"desktop", "android"})
    public void searchTest() {
        WebDriver driver = getDriver();
        HomePageBase homePage = initPage(driver, HomePageBase.class);
        homePage.open();
        homePage.getSearchSection().typeInSearchField("Ball");
        homePage.getSearchSection().clickSearchButton();
        ProductListingPageBase plp = initPage(driver, ProductListingPageBase.class);
        plp.validateProductName("Ball");
    }

    @Test(groups = {"desktop"})
    public void emptySearchTest() {
        WebDriver driver = getDriver();
        HomePageBase homePage = initPage(driver, HomePageBase.class);
        homePage.open();
        homePage.getSearchSection().clickSearchButton();
        CategoriesPageBase categoriesPage = initPage(driver, CategoriesPageBase.class);
        Assert.assertEquals(categoriesPage.getCategoriesTitle(), CATEGORIES_TITLE, "Element should have text \"All Categories\"");
        Assert.assertTrue(driver.getCurrentUrl().contains("all-categories"), "url should contain all-categories");
    }

    @Test(groups = {"desktop", "android"})
    public void noProductsFoundTest() {
        WebDriver driver = getDriver();
        HomePageBase homePage = initPage(driver, HomePageBase.class);
        homePage.open();
        homePage.getSearchSection().typeInSearchField("somenonexistingproduct");
        homePage.getSearchSection().clickSearchButton();
        ProductListingPageBase plp = initPage(driver, ProductListingPageBase.class);
        Assert.assertEquals(plp.getNoProductFoundLbl(), PRODUCTS_NOT_FOUND_MSG, "Message should say that no matches were found");
    }

    @Test(groups = {"desktop"})
    public void searchWithCategoriesTest() {
        WebDriver driver = getDriver();
        HomePageBase homePage = initPage(driver, HomePageBase.class);
        homePage.open();
        homePage.getSearchSection().typeInSearchField("Ball");
        final String category = "Music";
        homePage.getSearchSection().selectCategory(category);
        homePage.getSearchSection().clickSearchButton();
        ProductListingPageBase plp = initPage(driver, ProductListingPageBase.class);
        Assert.assertTrue(plp.isCategoryActive(category));
    }

    @Test(groups = {"desktop"})
    public void excludeProductsTest() {
        WebDriver driver = getDriver();
        HomePageBase homePage = initPage(driver, HomePageBase.class);
        homePage.open();
        AdvancedSearchPageBase advancedSearchPage = homePage.getSearchSection().goToAdvancedSearchPage();
        advancedSearchPage.typeIncludedWords("Ball");
        advancedSearchPage.typeExcludedWords("Disco");
        advancedSearchPage.submitFilter();
        ProductListingPageBase plp = initPage(driver, ProductListingPageBase.class);
        plp.validateProductName("Ball", "Disco");
    }

    @Test(groups = {"desktop"})
    public void includeExactWordsTest() {
        WebDriver driver = getDriver();
        HomePageBase homePage = initPage(driver, HomePageBase.class);
        homePage.open();
        AdvancedSearchPageBase advancedSearchPage = homePage.getSearchSection().goToAdvancedSearchPage();
        advancedSearchPage.typeIncludedWords("Disco Ball");
        advancedSearchPage.selectKeyWordOptions(KeyWordOptions.ExactWordsExactOrder);
        advancedSearchPage.submitFilter();
        ProductListingPageBase plp = initPage(driver, ProductListingPageBase.class);
        plp.validateProductName("disco ball");
    }

    @Test(groups = {"desktop"})
    public void searchByProductRangeTest() {
        WebDriver driver = getDriver();
        HomePageBase homePage = initPage(driver, HomePageBase.class);
        homePage.open();
        AdvancedSearchPageBase advancedSearchPage = homePage.getSearchSection().goToAdvancedSearchPage();
        advancedSearchPage.typeIncludedWords("Ball");
        BigDecimal enteredMinPrice = new BigDecimal(20);
        BigDecimal enteredMaxPrice = new BigDecimal(50);
        advancedSearchPage.enterMinPrice(enteredMinPrice);
        advancedSearchPage.enterMaxPrice(enteredMaxPrice);
        advancedSearchPage.submitFilter();
        ProductListingPageBase plp = initPage(driver, ProductListingPageBase.class);
        List<BigDecimal> prices = plp.getProductPrices();
        for (BigDecimal price : prices) {
            LOGGER.info(price.toString());
            Assert.assertTrue(
                    price.compareTo(enteredMinPrice) >= 0 && price.compareTo(enteredMaxPrice) <= 0,
                    "Price should be more than 20 and less than 50. actual price: " + price
            );
        }
    }
}
