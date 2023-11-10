package christmas.domain.discount;

import christmas.domain.Day;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public enum DiscountEvent {
    WEEKDAY_EVENT((price, day) -> DiscountPrice.from(2023), (day -> day.isWeekDay())),
    WEEKEND_EVENT((price, day) -> DiscountPrice.from(2023), (day -> day.isWeekend())),
    CHRISTMAS_EVENT((price, day) -> DiscountPrice.from(100 * (day.getDay() - 1) + 1000), (day -> day.isBeforeThan(25))),
    PROMOTION_EVENT((price, day) -> DiscountPrice.from(25000), (day -> day.isBeforeThan(30))),
    SPECIAL_EVENT((price, day) -> DiscountPrice.from(100), (day -> day.isSpecialDay()))
    ;

    private final BiFunction<DiscountPrice, Day, DiscountPrice> discountPrice; // DiscountPrice 금액을 말한다
    private final Predicate<Day> isEventDay; // 날짜 조건 (가능한 날)

    DiscountEvent(BiFunction<DiscountPrice, Day, DiscountPrice> discountPrice,
                  Predicate<Day> isEventDay) {
        this.discountPrice = discountPrice;
        this.isEventDay = isEventDay;
    }
}
