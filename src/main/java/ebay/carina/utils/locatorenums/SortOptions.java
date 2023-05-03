package ebay.carina.utils.locatorenums;

public enum SortOptions {
    BestMatch("Best Match"),
    TimeEndingSoon("Time: ending soonest"),
    TimeNewlyListed("Time: newly listed"),
    PriceAsc("Price + Shipping: lowest first"),
    PriceDesc("Price + Shipping: highest first"),
    Distance("Distance: nearest first");

    private final String sortOption;
    private SortOptions(String sortOption) {
        this.sortOption = sortOption;
    }

    public String getSortOption() {
        return sortOption;
    }
}
