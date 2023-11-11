package christmas.domain.receipt;

import christmas.domain.discount.DiscountType;
import java.util.ArrayList;

public class Receipt {
    // 영수증은 할인 전 전체 가격, 할인 적용 가능한 이벤트 리스트를 유지한다
    private final TotalPrice totalPrice;
    private final AcceptedEvents acceptedEvents;

    private Receipt(TotalPrice totalPrice, AcceptedEvents acceptedEvents) {
        this.totalPrice = totalPrice;
        this.acceptedEvents = checkLimitOfPrice(totalPrice, acceptedEvents);
    }

    public static Receipt of(TotalPrice totalPrice, AcceptedEvents acceptedEvents) {
        return new Receipt(totalPrice, acceptedEvents);
    }

    private AcceptedEvents checkLimitOfPrice(TotalPrice totalPrice, AcceptedEvents acceptedEvents) {
        if (totalPrice.isLowerThanLimit()) {
            // 제한 조건의 금액보다 낮으면
            return AcceptedEvents.of(new ArrayList<DiscountType>(), totalPrice);
        }
        return acceptedEvents;
    }

    public TotalPrice getTotalPrice() {
        return totalPrice;
    }

    public AcceptedEvents getAcceptedEvents() {
        return acceptedEvents;
    }
}
