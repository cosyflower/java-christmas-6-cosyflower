package christmas.discount;

public enum DiscountType {
    ;

    private final DiscountEvent discountEvent;
    private final DiscountEventName discountEventName;

    DiscountType(DiscountEvent discountEvent, DiscountEventName discountEventName) {
        this.discountEvent = discountEvent;
        this.discountEventName = discountEventName;
    }
}
