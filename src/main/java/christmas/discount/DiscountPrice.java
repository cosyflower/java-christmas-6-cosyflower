package christmas.discount;

public class DiscountPrice {
    private final int discountPriceValue;

    private DiscountPrice(int discountPriceValue) {
        this.discountPriceValue = discountPriceValue;
    }

    public static DiscountPrice from(int discountPriceValue) {
        return new DiscountPrice(discountPriceValue);
    }

    public int getDiscountPriceValue() {
        return discountPriceValue;
    }

    public boolean isBetweenPrice(int minInclusive, int maxExclusive) {
        return isSameAndHigherThan(minInclusive) && isLowerThan(maxExclusive);
    }

    private boolean isSameAndHigherThan(int otherValue) {
        return discountPriceValue >= otherValue;
    }

    private boolean isLowerThan(int otherValue) {
        return discountPriceValue < otherValue;
    }
}
