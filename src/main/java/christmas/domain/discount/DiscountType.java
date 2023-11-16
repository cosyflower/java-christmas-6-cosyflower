package christmas.domain.discount;

import christmas.domain.reservation.EventReservation;

public enum DiscountType {
    // 변동 금액
    WEEKDAY_DISCOUNT(DiscountEvent.WEEKDAY_EVENT, DiscountEventName.from("평일 할인")),
    WEEKEND_DISCOUNT(DiscountEvent.WEEKEND_EVENT, DiscountEventName.from("주말 할인")),
    // 고정 금액
    CHRISTMAS_DISCOUNT(DiscountEvent.CHRISTMAS_EVENT, DiscountEventName.from("크리스마스 디데이 할인")),
    PROMOTION_DISCOUNT(DiscountEvent.PROMOTION_EVENT, DiscountEventName.from("증정 이벤트")),
    SPECIAL_DISCOUNT(DiscountEvent.SPECIAL_EVENT, DiscountEventName.from("특별 할인"))
    ;

    private final DiscountEvent discountEvent;
    private final DiscountEventName discountEventName;

    DiscountType(DiscountEvent discountEvent, DiscountEventName discountEventName) {
        this.discountEvent = discountEvent;
        this.discountEventName = discountEventName;
    }

    public DiscountPrice findDiscountPrice(EventReservation eventReservation) {
        return discountEvent.applyDiscountPrice(eventReservation);
    }

    public boolean checkValidEvent(EventReservation eventReservation) {
        return discountEvent.isValidEvent(eventReservation);
    }

    public String getDiscountEventNameValue() {
        return discountEventName.getEventNameValue();
    }
}
