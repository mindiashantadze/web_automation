package ebay.carina.locatorenums;

public enum KeyWordOptions {
    AllWordsAnyOrder("All words, any order"),
    AnyWordsAnyOrder("Any words, any order"),
    ExactWordsExactOrder("Exact words, exact order"),
    ExactWordsAnyOrder("Exact words, any order");

    private final String keyWordOptions;

    KeyWordOptions(String keyWordOptions) {
        this.keyWordOptions = keyWordOptions;
    }

    public String getKeyWordOptions() {
        return keyWordOptions;
    }
}
