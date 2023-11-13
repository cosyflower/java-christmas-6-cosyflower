package christmas.domain.discount;

import static christmas.util.Constants.*;

import christmas.domain.menu.MenuType;
import christmas.domain.reservation.EventReservation;
import christmas.util.Constants;
import java.util.function.Function;
import java.util.function.Predicate;

public enum DiscountEvent {
    WEEKDAY_EVENT(eventReservation -> DiscountPrice.from(
            eventReservation.getSpecificMenuTypeTotal(MenuType.DESERT) * DISCOUNT_AMOUNT
    ), (eventReservation -> eventReservation.isWeekDay())),

    WEEKEND_EVENT(eventReservation -> DiscountPrice.from(
            eventReservation.getSpecificMenuTypeTotal(MenuType.MAIN_MENU) * DISCOUNT_AMOUNT)
            , (eventReservation -> eventReservation.isWeekend())),

    CHRISTMAS_EVENT(eventReservation -> DiscountPrice.from(
            DEFAULT_DISCOUNT_AMOUNT + eventReservation.getAccumulatedDays() * INCREASE_AMOUNT),
            (eventReservation -> eventReservation.isBeforeThan(CHRISTMAS_DAY))),

    PROMOTION_EVENT(eventReservation -> DiscountPrice.from(applyPromotion(eventReservation,
            PROMOTION_CRITERIA)),
            (eventReservation -> eventReservation.isBeforeThan(LAST_DAY))),

    SPECIAL_EVENT(eventReservation -> DiscountPrice.from(DEFAULT_DISCOUNT_AMOUNT),
            (eventReservation -> eventReservation.isSpecialDay())),
    ;

    private final Function<EventReservation, DiscountPrice> discountPriceFunction;
    private final Predicate<EventReservation> isEventDay;

    DiscountEvent(
            Function<EventReservation, DiscountPrice> discountPriceFunction,
            Predicate<EventReservation> isEventDay) {
        this.discountPriceFunction = discountPriceFunction;
        this.isEventDay = isEventDay;
    }

    private static int applyPromotion(EventReservation eventReservation, int promotionCriteria) {
        if (eventReservation.checkPromotion(promotionCriteria)) {
            return Constants.PROMOTION_AMOUNT;
        }
        return INIT_VALUE;
    }

    public DiscountPrice applyDiscountPrice(EventReservation eventReservation) {
        return discountPriceFunction.apply(eventReservation);
    }

    public boolean isValidEvent(EventReservation eventReservation) {
        return isEventDay.test(eventReservation);
    }
}
