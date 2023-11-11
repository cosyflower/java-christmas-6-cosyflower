package christmas.domain.discount;

import christmas.domain.Day;
import java.util.function.Function;
import java.util.function.Predicate;

public enum DiscountEvent { // 유닛 당 할인 금액 or 고정 금액 그리고 혜택 적용 가능한 조건
    WEEKDAY_EVENT(day -> DiscountPrice.from(2023), (day -> day.isWeekDay())),
    WEEKEND_EVENT(day -> DiscountPrice.from(2023), (day -> day.isWeekend())),
    CHRISTMAS_EVENT(day -> DiscountPrice.from(100 * (day.getDay() - 1) + 1000), (day -> day.isBeforeThan(25))),
    PROMOTION_EVENT(day -> DiscountPrice.from(25000), (day -> day.isBeforeThan(31))),
    SPECIAL_EVENT(day -> DiscountPrice.from(100), (day -> day.isSpecialDay()))
    ;

    private final Function<Day, DiscountPrice> discountPriceFunction; // DiscountPrice 금액을 말한다
    private final Predicate<Day> isEventDay; // 날짜 조건 (가능한 날)

    DiscountEvent(
            Function<Day, DiscountPrice> discountPriceFunction,
            Predicate<Day> isEventDay) {
        this.discountPriceFunction = discountPriceFunction;
        this.isEventDay = isEventDay;
    }

    public DiscountPrice getDiscountPrice(Day day) {
        if (!isEventDay.test(day)) {
            return DiscountPrice.from(0);
        }
        return discountPriceFunction.apply(day);
    }

    public boolean isValidEvent(Day day) {
        return isEventDay.test(day);
    }
}
