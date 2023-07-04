package ebay.carina.utils.web_processor;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import ebay.carina.utils.lambdas.IElementProcessor;

public class WebElementProcessor <T>{
    public static String getText(ExtendedWebElement element) {
        return process(element, () -> element.getText().trim());
    }

    private static String process(ExtendedWebElement element, IElementProcessor<String> processor) {
        return processor.process();
    }
}
