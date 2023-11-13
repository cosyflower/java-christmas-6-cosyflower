package christmas.domain.discount;

import christmas.domain.EventReservation;
import java.util.Arrays;
import java.util.List;

public class DiscountChecker {
    //가능한 DiscountType 확인하기
    public static List<DiscountType> checkValidDiscountType(EventReservation eventReservation) {
        return Arrays.stream(DiscountType.values())
                .filter(discountType -> discountType.checkValidEvent(eventReservation))
                .toList();
    }

    public static List<DiscountPrice> findAllDiscountPrice(EventReservation eventReservation) { // 할인 금액 확인
        // List<DiscountPrice> 반환
        // 적용 가능한 날짜인지 먼저 확인하고
        // 할인 금액을 각각 확인한다
        return Arrays.stream(DiscountType.values())
                .filter(discountType -> discountType.checkValidEvent(eventReservation))
                .map(discountType -> discountType.findDiscountPrice(eventReservation))
                .toList();
    }

    public static int getDiscountPriceTotal(EventReservation eventReservation) {
        // 전체 할인된 금액 확인하기
        return findAllDiscountPrice(eventReservation)
                .stream()
                .mapToInt(DiscountPrice::getDiscountPriceValue)
                .sum();
    }
}
