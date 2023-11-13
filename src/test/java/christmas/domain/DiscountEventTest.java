package christmas.domain;

import christmas.domain.discount.DiscountEvent;
import christmas.domain.order.AcceptedOrders;
import christmas.domain.order.Order;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DiscountEventTest {
    private static Stream<Arguments> dayAndEvent() {
        return Stream.of(
                Arguments.of(25, DiscountEvent.WEEKDAY_EVENT, true),
                Arguments.of(25, DiscountEvent.WEEKEND_EVENT, false),
                Arguments.of(25, DiscountEvent.SPECIAL_EVENT, true),
                Arguments.of(25, DiscountEvent.CHRISTMAS_EVENT, true),
                Arguments.of(25, DiscountEvent.PROMOTION_EVENT, true),

                Arguments.of(22, DiscountEvent.WEEKDAY_EVENT, false),
                Arguments.of(22, DiscountEvent.WEEKEND_EVENT, true),
                Arguments.of(22, DiscountEvent.SPECIAL_EVENT, false),
                Arguments.of(22, DiscountEvent.CHRISTMAS_EVENT, true),
                Arguments.of(22, DiscountEvent.PROMOTION_EVENT, true)
        );

    }

    @ParameterizedTest(name = " 날짜 : {0} 이벤트 : {1} 결과 : {2} ")
    @MethodSource("dayAndEvent")
    void WeekDay(int dayValue, DiscountEvent discountEvent, Boolean validOrUnvalid) {
        Day day = Day.from(dayValue);
        boolean isValid = discountEvent.isValidEvent(day);

        Assertions.assertThat(isValid).isEqualTo(validOrUnvalid);
    }

}
