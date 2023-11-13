package christmas.domain.receipt;

import christmas.domain.discount.DiscountPrice;
import christmas.domain.discount.DiscountType;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class EventStatus {
    // 할인 별 할인 받은 금액 확인
    private final Map<DiscountType, DiscountPrice> eventStatus = new EnumMap<>(DiscountType.class);

    public EventStatus() {
        // EventStatus - 0원인 경우 포함하지 않도록 하기
        // 시작은 각각의 DiscountTpye 그리고 0원으로 넣어야 한다
        Arrays.stream(DiscountType.values())
                .forEach(discountType -> eventStatus.put(discountType, DiscountPrice.from(0)));
    }

    public void addStatus(DiscountType discountType, DiscountPrice discountPrice) {
        eventStatus.replace(discountType, discountPrice);
    }

    public Map<DiscountType, DiscountPrice> getEventStatus() {
        return Collections.unmodifiableMap(eventStatus);
    }
}
