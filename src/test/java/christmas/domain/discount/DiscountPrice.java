package christmas.domain.discount;

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
}
