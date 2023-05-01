package ebay.carina;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import ebay.carina.utils.context.MobileContextUtils;
import ebay.carina.utils.locatorenums.CategoryOption;
import ebay.carina.utils.locatorenums.FilterOptions;
import ebay.carina.utils.locatorenums.SortOptions;
import ebay.carina.pages.common.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

public class SearchTest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchTest.class);

    private static final String SIGNIN_PAGE_MSG = "Sign in to eBay or create an account";
    private final static String PRODUCTS_NOT_FOUND_MSG = "No exact matches found";

    @Test
    public void searchTest() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.getSearchSection().typeInSearchField("Ball");
        homePage.getSearchSection().clickSearchButton();
        ProductListingPageBase plp = initPage(getDriver(), ProductListingPageBase.class);
        plp.validateProductName("Ball");
    }

    @Test
    public void saveSearchTest() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.getSearchSection().typeInSearchField("Ball");
        homePage.getSearchSection().clickSearchButton();
        ProductListingPageBase plp = initPage(getDriver(), ProductListingPageBase.class);;
        LoginPageBase loginPage = plp.clickSaveSearch();
        Assert.assertEquals(loginPage.getSignInMessage(), SIGNIN_PAGE_MSG);
    }

    @Test
    public void noProductsFoundTest() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.getSearchSection().typeInSearchField("somenonexistingproduct");
        homePage.getSearchSection().clickSearchButton();
        ProductListingPageBase plp = initPage(getDriver(), ProductListingPageBase.class);
        Assert.assertEquals(plp.getNoProductFoundLbl(), PRODUCTS_NOT_FOUND_MSG, "Message should say that no matches were found");
    }

    @Test
    public void searchWithCategoriesTest() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.getSearchSection().typeInSearchField("Ball");
        final String category = "Music";
        homePage.getSearchSection().clickSearchButton();
        ProductListingPageBase plp = initPage(getDriver(), ProductListingPageBase.class);
        plp.selectCategory(CategoryOption.MUSIC);
        plp = initPage(getDriver(), ProductListingPageBase.class);
        Assert.assertTrue(plp.isCategoryActive(category), "Category is not active");
    }

    @Test
    public void priceRangeTest() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.getSearchSection().typeInSearchField("Ball");
        homePage.getSearchSection().clickSearchButton();
        ProductListingPageBase plp = initPage(getDriver(), ProductListingPageBase.class);
        plp.getFilter().clickFilterBtn();
        BigDecimal enteredMinPrice = new BigDecimal(20);
        BigDecimal enteredMaxPrice = new BigDecimal(50);
        plp.getFilter().typeMinPrice(enteredMinPrice);
        plp.getFilter().typeMaxPrice(enteredMaxPrice);
        plp.getFilter().submitPriceRange();
        List<BigDecimal> prices = plp.getProductPrices();
        for (BigDecimal price : prices) {
            Assert.assertTrue(
                    price.compareTo(enteredMinPrice) >= 0 && price.compareTo(enteredMaxPrice) <= 0,
                    "Price should be more than 20 and less than 50. actual price: " + price
            );
        }
    }

    @Test
    public void sortingProductsTest() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.getSearchSection().typeInSearchField("Ball");
        homePage.getSearchSection().clickSearchButton();
        ProductListingPageBase plp = initPage(getDriver(), ProductListingPageBase.class);
        plp.selectSortingOption(SortOptions.PriceAsc);
        plp = initPage(getDriver(), ProductListingPageBase.class);
        List<BigDecimal> prices = plp.getProductPricesWithShipping();
        for (int i = 1; i < prices.size(); i++) {
            BigDecimal previousPrice = prices.get(i - 1);
            BigDecimal price = prices.get(i);
            Assert.assertTrue(previousPrice.compareTo(price) <= 0, price.toString() + " should be greater than " + previousPrice.toString());
        };
    }

    @Test
    public void freeShippingTest() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        homePage.getSearchSection().typeInSearchField("Ball");
        homePage.getSearchSection().clickSearchButton();
        ProductListingPageBase plp = initPage(getDriver(), ProductListingPageBase.class);
        plp.selectFreeShippingOption();
        plp.selectOption(FilterOptions.FREE_SHIPPING);
        plp.validateFreeShipping();
    }

    @Test
    public void androidNoProductsFoundTest() {
        WebDriver driver = getDriver();
        HomePageBase homePage = initPage(driver, HomePageBase.class);
        homePage.open();
        homePage.getSearchSection().typeInSearchField("somenonexistingproduct");

        // demo context switch for task 2
        MobileContextUtils contextUtils = new MobileContextUtils();
        contextUtils.switchMobileContext(MobileContextUtils.View.NATIVE, null);
        NativePageBase nativePageBase = initPage(getDriver(), NativePageBase.class);

        nativePageBase.clickMenu();
        nativePageBase.clickNewTab();
        nativePageBase.clickTabSwitcher();
        nativePageBase.clickCloseTab();
        nativePageBase.clickTabThumbnail();

        // asserting the product search result
        contextUtils.switchMobileContext(MobileContextUtils.View.WEB_CHROME, null);
        homePage.getSearchSection().clickSearchButton();
        ProductListingPageBase plp = initPage(driver, ProductListingPageBase.class);
        Assert.assertEquals(plp.getNoProductFoundLbl(), PRODUCTS_NOT_FOUND_MSG, "Message should say that no matches were found");
    }
}
