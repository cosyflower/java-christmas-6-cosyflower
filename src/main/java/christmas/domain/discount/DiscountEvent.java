package christmas.domain.discount;

import christmas.domain.Day;
import christmas.domain.EventReservation;
import christmas.domain.menu.MenuType;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public enum DiscountEvent { // 유닛 당 할인 금액 or 고정 금액 그리고 혜택 적용 가능한 조건
    WEEKDAY_EVENT(eventReservation -> DiscountPrice.from(
            eventReservation.getSpecificMenuTypeTotal(MenuType.DESERT) * 2023
    ), (day -> day.isWeekDay())),

    WEEKEND_EVENT(eventReservation -> DiscountPrice.from(
            eventReservation.getSpecificMenuTypeTotal(MenuType.MAIN_MENU) * 2023)
            , (day -> day.isWeekend())),

    CHRISTMAS_EVENT(eventReservation -> DiscountPrice.from(1000 + eventReservation.getAccumulatedDays() * 100),
            (day -> day.isBeforeThan(25))),

    PROMOTION_EVENT(eventReservation -> DiscountPrice.from(applyPromotion(eventReservation, 120000)),
            (day -> day.isBeforeThan(31))),

    SPECIAL_EVENT(eventReservation -> DiscountPrice.from(1000), (day -> day.isSpecialDay())),
    ;

    private final Function<EventReservation, DiscountPrice> discountPriceFunction; // DiscountPrice 금액을 말한다
    private final Predicate<Day> isEventDay; // 날짜 조건 (가능한 날)

    DiscountEvent(
            Function<EventReservation, DiscountPrice> discountPriceFunction,
            Predicate<Day> isEventDay) {
        this.discountPriceFunction = discountPriceFunction;
        this.isEventDay = isEventDay;
    }

    private static int applyPromotion(EventReservation eventReservation, int promotionCriteria) {
        if (eventReservation.checkPromotion(promotionCriteria)) {
            return 25000;
        }
        return 0;
    }

    public boolean isValidEvent(Day day) {
        return isEventDay.test(day);
    }

    public List<DiscountPrice> findAllDiscountPrice(EventReservation eventReservation) { // 할인 금액 확인
        // List<DiscountPrice> 반환
        // 적용 가능한 날짜인지 먼저 확인하고
        // 할인 금액을 각각 확인한다
        return Arrays.stream(values())
                .filter(discountEvent -> isEventDay.test(eventReservation.getDay()))
                .map(discountEvent -> discountPriceFunction.apply(eventReservation))
                .toList();
    }

    public int getDiscountPriceTotal(EventReservation eventReservation) {
        return findAllDiscountPrice(eventReservation).stream()
                .mapToInt(DiscountPrice::getDiscountPriceValue)
                .sum();
    }
}
