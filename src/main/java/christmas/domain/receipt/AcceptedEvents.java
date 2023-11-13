package christmas.domain.receipt;

import christmas.domain.discount.DiscountType;
import java.util.ArrayList;
import java.util.List;

public class AcceptedEvents {
    // 가능한 이벤트 (DiscountChecker - checkValidDiscountTypes
    // 인자로 받는다 (날짜만 적용한다)
    private final List<DiscountType> acceptedEvents;

    private AcceptedEvents(List<DiscountType> acceptedEvents) {
        // EventStatus - 0원인 경우 포함하지 않도록 하기
        this.acceptedEvents = new ArrayList<>(acceptedEvents);
    }

    public static AcceptedEvents of(List<DiscountType> discountTypes) {
        return new AcceptedEvents(discountTypes);
    }

}
