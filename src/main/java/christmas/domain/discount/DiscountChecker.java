package christmas.domain.discount;

import christmas.domain.receipt.EventStatus;
import christmas.domain.receipt.TotalPrice;
import christmas.domain.reservation.EventReservation;
import java.util.Arrays;
import java.util.List;

public class DiscountChecker {
    // private 화 해야 한다
    public static List<DiscountType> checkValidDiscountTypes(EventReservation eventReservation) {
        return Arrays.stream(DiscountType.values())
                .filter(discountType -> discountType.checkValidEvent(eventReservation))
                .toList();
    }

    public static TotalPrice getDiscountPriceTotal(EventStatus eventStatus) {
        return TotalPrice.from(eventStatus.getBenefitAmount());
    }

    public static EventStatus findAllDiscountPrice(EventReservation eventReservation) { // 할인 금액 확인
        EventStatus eventStatus = new EventStatus();
        // 총주문 금액 10000원 작으면 생성하지 않는다 - EventReservation
        if (eventReservation.getTotalPriceWithoutDiscount() < 10000) {
            return eventStatus;
        }
        checkValidDiscountTypes(eventReservation).stream()
                .forEach(discountType -> eventStatus.addStatus(discountType,
                        discountType.findDiscountPrice(eventReservation)));
        return eventStatus;
    }


}
