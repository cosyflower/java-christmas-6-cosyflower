package christmas.receipt;

import christmas.domain.discount.DiscountEvent;
import christmas.domain.discount.DiscountType;
import java.util.Arrays;
import java.util.EnumMap;

public class EventStatus {
    private final EnumMap<DiscountEvent, Integer> eventStatus = new EnumMap<>(DiscountEvent.class);

    public EventStatus() {
        // 각 DiscountEvent - 할인 금액 그리고 혜택 가능한 Day
        // DiscountEvent 그리고 0원으로 모두 시작해야 한다
        Arrays.stream(DiscountType.values())
                .forEach(discountType -> eventStatus.put(discountType.getDiscountEvent(), 0));
    }
}
