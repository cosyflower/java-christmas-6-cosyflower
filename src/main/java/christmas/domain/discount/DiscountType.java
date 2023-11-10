package christmas.domain.discount;

public enum DiscountType {
    WEEKDAY_DISCOUNT(DiscountEvent.WEEKDAY_EVENT, DiscountEventName.from("평일 할인")),
    WEEKEND_DISCOUNT(DiscountEvent.WEEKEND_EVENT, DiscountEventName.from("주말 할인")),
    CHRISTMAS_DISCOUNT(DiscountEvent.CHRISTMAS_EVENT, DiscountEventName.from("크리스마스 할인")),
    PROMOTION_DISCOUNT(DiscountEvent.PROMOTION_EVENT, DiscountEventName.from("증정 할인")),
    SPECIAL_DISCOUNT(DiscountEvent.SPECIAL_EVENT, DiscountEventName.from("스페셜 할인"))
    ;

    private final DiscountEvent discountEvent;
    private final DiscountEventName discountEventName;

    DiscountType(DiscountEvent discountEvent, DiscountEventName discountEventName) {
        this.discountEvent = discountEvent;
        this.discountEventName = discountEventName;
    }

    // 적용가능한 할인이 무엇인지 알 수 있어야 한다
    // Day를 인자로 넘겨줘야 한다
    // List<DiscountType>을 반환하는 방향으로 진행한다 (적용 가능한 할인 리스트)

}
