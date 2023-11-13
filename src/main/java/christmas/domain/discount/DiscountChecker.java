package christmas.domain.discount;

import christmas.domain.receipt.EventStatus;
import christmas.domain.reservation.EventReservation;
import java.util.Arrays;
import java.util.List;

public class DiscountChecker {
    private static final int DISCOUNT_THRESHOLD = 10000;
    // private 화 해야 한다
    public static List<DiscountType> checkValidDiscountTypes(EventReservation eventReservation) {
        return Arrays.stream(DiscountType.values())
                .filter(discountType -> discountType.checkValidEvent(eventReservation))
                .toList();
    }

    public static EventStatus findAllDiscountPrice(EventReservation eventReservation) {
        EventStatus eventStatus = new EventStatus();
        if (eventReservation.getTotalPriceWithoutDiscount() < DISCOUNT_THRESHOLD) {
            return eventStatus;
        }
        checkValidDiscountTypes(eventReservation)
                .forEach(discountType -> eventStatus.addStatus(discountType,
                        discountType.findDiscountPrice(eventReservation)));
        return eventStatus;
    }


}
