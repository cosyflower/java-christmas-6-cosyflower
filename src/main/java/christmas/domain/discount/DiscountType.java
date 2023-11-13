package christmas.domain.discount;

import christmas.domain.reservation.EventReservation;

public enum DiscountType { // 이벤트 관련 총 정보
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
    // 각 이벤트 별 할인하는 메를 작성한다
    DiscountType(DiscountEvent discountEvent, DiscountEventName discountEventName) {
        this.discountEvent = discountEvent;
        this.discountEventName = discountEventName;
    }

    public DiscountPrice findDiscountPrice(EventReservation eventReservation) {
        return discountEvent.applyDiscountPrice(eventReservation);
    }

    // chekcValidDiscountType()
    public boolean checkValidEvent(EventReservation eventReservation) {
        return discountEvent.isValidEvent(eventReservation);
    }

    public String getDiscountEventNameValue() {
        return discountEventName.getEventNameVaue();
    }
}
