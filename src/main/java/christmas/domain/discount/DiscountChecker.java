package christmas.domain.discount;

import christmas.domain.EventReservation;
import christmas.domain.receipt.EventStatus;
import java.util.Arrays;
import java.util.List;

public class DiscountChecker {
    public static List<DiscountType> checkValidDiscountTypes(EventReservation eventReservation) {
        return Arrays.stream(DiscountType.values())
                .filter(discountType -> discountType.checkValidEvent(eventReservation))
                .toList();
    }

    public static EventStatus findAllDiscountPrice(EventReservation eventReservation) { // 할인 금액 확인
        EventStatus eventStatus = new EventStatus();
        List<DiscountType> discountTypes = checkValidDiscountTypes(eventReservation);
        // 가능한 이벤트 리스트
        discountTypes.stream()
                .forEach(discountType -> eventStatus.addStatus(discountType,
                        discountType.findDiscountPrice(eventReservation)));
        return eventStatus;
    }

//    public static int getDiscountPriceTotal(EventReservation eventReservation) {
//        return findAllDiscountPrice(eventReservation)
//                .stream()
//                .mapToInt(DiscountPrice::getDiscountPriceValue)
//                .sum();
//    }
}
