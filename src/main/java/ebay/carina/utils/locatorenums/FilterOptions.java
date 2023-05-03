package ebay.carina.utils.locatorenums;

public enum FilterOptions {
    PRICE("Price"),
    CONDITION("Condition"),
    CATEGORY("Category"),
    SORT("Sort"),
    BUYING_FORMAT("Buying Format"),
    SHIPPING("Shipping"),
    FREE_SHIPPING("Free International Shipping");

    private final String filterOption;

    private FilterOptions(String filterOption) {
        this.filterOption = filterOption;
    }

    public String getFilterOptions() {
        return this.filterOption;
    }
}
