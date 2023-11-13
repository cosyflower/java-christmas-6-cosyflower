package christmas.domain.discount;

import christmas.domain.EventReservation;
import christmas.domain.menu.MenuProduct;
import christmas.domain.menu.MenuType;
import java.util.function.Function;
import java.util.function.Predicate;

public enum DiscountEvent { // 유닛 당 할인 금액 or 고정 금액 그리고 혜택 적용 가능한 조건
    WEEKDAY_EVENT(eventReservation -> DiscountPrice.from(
            eventReservation.getSpecificMenuTypeTotal(MenuType.DESERT) * 2023
    ), (eventReservation -> eventReservation.isWeekDay())),

    WEEKEND_EVENT(eventReservation -> DiscountPrice.from(
            eventReservation.getSpecificMenuTypeTotal(MenuType.MAIN_MENU) * 2023)
            , (eventReservation -> eventReservation.isWeekend())),

    CHRISTMAS_EVENT(eventReservation -> DiscountPrice.from(1000 + eventReservation.getAccumulatedDays() * 100),
            (eventReservation -> eventReservation.isBeforeThan(25))),

    PROMOTION_EVENT(eventReservation -> DiscountPrice.from(applyPromotion(eventReservation, 120000)),
            (eventReservation -> eventReservation.isBeforeThan(31))),

    SPECIAL_EVENT(eventReservation -> DiscountPrice.from(1000),
            (eventReservation -> eventReservation.isSpecialDay())),
    ;

    private final Function<EventReservation, DiscountPrice> discountPriceFunction; // DiscountPrice 금액을 말한다
    private final Predicate<EventReservation> isEventDay; // 날짜 조건 (가능한 날)

    DiscountEvent(
            Function<EventReservation, DiscountPrice> discountPriceFunction,
            Predicate<EventReservation> isEventDay) { // Predicate<EventReservation>
        this.discountPriceFunction = discountPriceFunction;
        this.isEventDay = isEventDay;
    }

    private static int applyPromotion(EventReservation eventReservation, int promotionCriteria) {
        if (eventReservation.checkPromotion(promotionCriteria)) {
            return 25000;
        }
        return 0;
    }

    public DiscountPrice applyDiscountPrice(EventReservation eventReservation) { // 할인 금액 확인
        // 할인 금액을 각각 확인한다
        return discountPriceFunction.apply(eventReservation);
    }

    public boolean isValidEvent(EventReservation eventReservation) {
        return isEventDay.test(eventReservation);
    }
}
