package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.Day;
import christmas.discount.DiscountEvent;
import christmas.discount.DiscountPrice;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DiscountEventTest {
    private static Stream<Arguments> dayAndEvent() {
        return Stream.of(
                Arguments.of(25, DiscountEvent.WEEKDAY_EVENT, 2023),
                Arguments.of(25, DiscountEvent.WEEKEND_EVENT, 0),
                Arguments.of(25, DiscountEvent.SPECIAL_EVENT, 100),
                Arguments.of(25, DiscountEvent.CHRISTMAS_EVENT, 3400),
                Arguments.of(25, DiscountEvent.PROMOTION_EVENT, 25000),

                Arguments.of(22, DiscountEvent.WEEKDAY_EVENT, 0),
                Arguments.of(22, DiscountEvent.WEEKEND_EVENT, 2023),
                Arguments.of(22, DiscountEvent.SPECIAL_EVENT, 0),
                Arguments.of(22, DiscountEvent.CHRISTMAS_EVENT, 3100),
                Arguments.of(22, DiscountEvent.PROMOTION_EVENT, 25000)
        );

    }

    @ParameterizedTest(name = " 날짜 : {0} 이벤트 : {1} 결과 : {2} ")
    @MethodSource("dayAndEvent")
    void WeekDay(int dayValue, DiscountEvent discountEvent, int discountPrice) {
        Day day = Day.from(dayValue);

        DiscountPrice weekDayDiscount = discountEvent.getDiscountPrice(day);

        assertThat(weekDayDiscount.getDiscountPriceValue()).isEqualTo(discountPrice);
    }

    // weekday, weekend 구분해서 데이터 전달하는 방향으로 작성하기
}
