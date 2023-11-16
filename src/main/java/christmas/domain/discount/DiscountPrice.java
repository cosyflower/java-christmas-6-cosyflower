package christmas.domain.discount;

public class DiscountPrice {
    private static final int VALID_CRITERIA = 0;
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

    public boolean isValidDiscount() {
        return discountPriceValue > VALID_CRITERIA;
    }

    public boolean isUnvalidDiscount() {
        return !isValidDiscount();
    }
}
