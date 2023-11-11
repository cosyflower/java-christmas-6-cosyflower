package christmas;

import christmas.domain.discount.DiscountType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AcceptedEvents {
    // 가능한 이벤트
    private final List<DiscountType> acceptedEvents;

    private AcceptedEvents(List<DiscountType> acceptedEvents, TotalPrice totalPrice) {
        this.acceptedEvents = new ArrayList<>(checkPromotion(acceptedEvents, totalPrice));
    }

    public static AcceptedEvents from(List<DiscountType> discountTypes, TotalPrice totalPrice) {
        return new AcceptedEvents(discountTypes, totalPrice);
    }

    public List<DiscountType> checkPromotion(List<DiscountType> discountTypes, TotalPrice totalPrice) {
        if (totalPrice.isLowerThanPromotionCriteria()) {
            return discountTypes.stream()
                    .filter(discountType -> discountType != DiscountType.PROMOTION_DISCOUNT)
                    .toList();
        }
        return discountTypes;
    }

    public List<DiscountType> getAcceptedEvents() {
        return Collections.unmodifiableList(acceptedEvents);
    }
}
