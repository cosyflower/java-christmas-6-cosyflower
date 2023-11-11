package christmas.domain.discount;

import christmas.domain.Day;
import java.util.Arrays;
import java.util.List;

public enum DiscountType { // 이벤트 관련 총 정보
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

    // 가능한 DiscountType을 받는다
    public static List<DiscountType> checkValidDiscountType(Day day) {
        return Arrays.stream(values())
                .filter(discountType -> discountType.checkValidEvent(day))
                .toList();
    }
    // chekcValidDiscountType()
    private boolean checkValidEvent(Day day) {
        return discountEvent.isValidEvent(day);
    }

    public DiscountEvent getDiscountEvent() {
        return discountEvent;
    }
}
