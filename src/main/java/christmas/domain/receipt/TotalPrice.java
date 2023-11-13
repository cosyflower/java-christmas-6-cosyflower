package christmas.domain.receipt;

public class TotalPrice {
    private final int totalPriceValue;

    private TotalPrice(int totalPrice) {
        this.totalPriceValue = totalPrice;
    }

    public static TotalPrice from(int totalPrice) {
        return new TotalPrice(totalPrice);
    }

    public int getTotalPriceValue() {
        return totalPriceValue;
    }

    public boolean isBetweenPrice(int minInclusive, int maxExclusive) {
        return isSameAndHigherThan(minInclusive) && isLowerThan(maxExclusive);
    }

    private boolean isSameAndHigherThan(int otherValue) {
        return totalPriceValue >= otherValue;
    }

    private boolean isLowerThan(int otherValue) {
        return totalPriceValue < otherValue;
    }
}
