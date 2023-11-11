package christmas.domain.receipt;

import christmas.domain.discount.DiscountType;
import christmas.domain.order.AcceptedOrders;
import java.util.ArrayList;

public class Receipt {
    // 영수증은 할인 전 전체 가격, 할인 적용 가능한 이벤트 리스트를 유지한다
    private final TotalPrice totalPrice;
    private final AcceptedEvents acceptedEvents;
    private final AcceptedOrders acceptedOrders;


    private Receipt(AcceptedEvents acceptedEvents, AcceptedOrders acceptedOrders) {
        this.acceptedOrders = acceptedOrders;
        this.totalPrice = TotalPrice.from(acceptedOrders.checkTotalPriceWithoutDiscount());
        this.acceptedEvents = checkLimitOfPrice(totalPrice, acceptedEvents);

    }

    public static Receipt of(AcceptedEvents acceptedEvents, AcceptedOrders acceptedOrders) {
        return new Receipt(acceptedEvents, acceptedOrders);
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
