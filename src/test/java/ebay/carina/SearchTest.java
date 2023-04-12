package ebay.carina;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import ebay.carina.locatorenums.KeyWordOptions;
import ebay.carina.pages.AdvancedSearchPage;
import ebay.carina.pages.CategoriesPage;
import ebay.carina.pages.HomePage;
import ebay.carina.pages.ProductListingPage;
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

    @Test()
    public void searchTest() {
        WebDriver driver = getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.getSearchSection().typeInSearchField("Ball");
        homePage.getSearchSection().clickSearchButton();
        ProductListingPage plp = new ProductListingPage(driver);
        plp.validateProductName("Ball");
    }

    @Test
    public void emptySearchTest() {
        WebDriver driver = getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.getSearchSection().clickSearchButton();
        CategoriesPage categoriesPage = new CategoriesPage(driver);
        Assert.assertEquals(categoriesPage.getCategoriesTitle(), CATEGORIES_TITLE, "Element should have text \"All Categories\"");
        Assert.assertTrue(driver.getCurrentUrl().contains("all-categories"), "url should contain all-categories");
    }

    @Test
    public void noProductsFoundTest() {
        WebDriver driver = getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.getSearchSection().typeInSearchField("somenonexistingproduct");
        homePage.getSearchSection().clickSearchButton();
        ProductListingPage plp = new ProductListingPage(driver);
        Assert.assertEquals(plp.getNoProductFoundLbl(), PRODUCTS_NOT_FOUND_MSG, "Message should say that no matches were found");
    }

    @Test
    public void searchWithCategoriesTest() {
        WebDriver driver = getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.getSearchSection().typeInSearchField("Ball");
        final String category = "Music";
        homePage.getSearchSection().selectCategory(category);
        homePage.getSearchSection().clickSearchButton();
        ProductListingPage plp = new ProductListingPage(driver);
        Assert.assertTrue(plp.isCategoryActive(category));
    }

    @Test
    public void excludeProductsTest() {
        WebDriver driver = getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        AdvancedSearchPage advancedSearchPage = homePage.getSearchSection().goToAdvancedSearchPage();
        advancedSearchPage.typeIncludedWords("Ball");
        advancedSearchPage.typeExcludedWords("Disco");
        advancedSearchPage.submitFilter();
        ProductListingPage plp = new ProductListingPage(driver);
        plp.validateProductName("Ball", "Disco");
    }

    @Test
    public void includeExactWordsTest() {
        WebDriver driver = getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        AdvancedSearchPage advancedSearchPage = homePage.getSearchSection().goToAdvancedSearchPage();
        advancedSearchPage.typeIncludedWords("Disco Ball");
        advancedSearchPage.selectKeyWordOptions(KeyWordOptions.ExactWordsExactOrder);
        advancedSearchPage.submitFilter();
        ProductListingPage plp = new ProductListingPage(driver);
        plp.validateProductName("disco ball");
    }

    @Test
    public void searchByProductRangeTest() {
        WebDriver driver = getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        AdvancedSearchPage advancedSearchPage = homePage.getSearchSection().goToAdvancedSearchPage();
        advancedSearchPage.typeIncludedWords("Ball");
        BigDecimal enteredMinPrice = new BigDecimal(20);
        BigDecimal enteredMaxPrice = new BigDecimal(50);
        advancedSearchPage.enterMinPrice(enteredMinPrice);
        advancedSearchPage.enterMaxPrice(enteredMaxPrice);
        advancedSearchPage.submitFilter();
        ProductListingPage plp = new ProductListingPage(driver);
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
