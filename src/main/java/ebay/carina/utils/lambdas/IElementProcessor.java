package ebay.carina.utils.lambdas;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@FunctionalInterface
public interface IElementProcessor<T> {
    public T process();
}
