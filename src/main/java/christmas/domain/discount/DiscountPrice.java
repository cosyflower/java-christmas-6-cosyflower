package christmas.domain.discount;

public class DiscountPrice { // 할인 금액, 할인 금액의 합
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
        return discountPriceValue > 0;
    }
}
