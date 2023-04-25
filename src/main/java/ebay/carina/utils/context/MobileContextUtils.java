package ebay.carina.utils.context;

import com.zebrunner.carina.webdriver.DriverHelper;
import com.zebrunner.carina.webdriver.IDriverPool;
import ebay.carina.pages.android.ProductListingPage;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.ContextAware;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class MobileContextUtils implements IDriverPool {
    private final static Logger LOGGER = LoggerFactory.getLogger(MobileContextUtils.class);

    public void switchMobileContext(View context, View exclude) {
        WebDriver driver = getDriver();
        DriverHelper helper = new DriverHelper();
        Set<String> contextHandles = helper.performIgnoreException(((ContextAware) driver)::getContextHandles);
        String desiredContext = "";
        boolean isContextPresent = false;
        LOGGER.info("Existing Contexts: ");
        for (String c : contextHandles) {
            if (c.contains(context.getView())) {
                if (exclude != null && c.contains(exclude.getView())) {
                    continue;
                }
                desiredContext = c;
                isContextPresent = true;
            }
            LOGGER.info(c);
        }
        if (!isContextPresent) {
            throw new NotFoundException("Desired context is not present");
        }

        LOGGER.info("Switching to context " + desiredContext);
        ((SupportsContextSwitching) driver).context(desiredContext);
    }

    public enum View {
        NATIVE("NATIVE_APP"),
        WEB_CHROME("CHROMIUM");

        String viewName;

        View(String viewName) {
            this.viewName = viewName;
        }

        public String getView() {
            return this.viewName;
        }
    }
}
