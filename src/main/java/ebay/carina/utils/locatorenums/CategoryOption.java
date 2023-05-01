package ebay.carina.utils.locatorenums;

public enum CategoryOption {
    MUSIC("Music"),
    SHOW_MORE("Show More");
    String category = "";
    CategoryOption(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }
}
