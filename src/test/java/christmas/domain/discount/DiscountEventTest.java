package christmas.domain.discount;

import christmas.domain.order.AcceptedOrders;
import christmas.domain.order.Order;
import christmas.domain.reservation.Day;
import christmas.domain.reservation.EventReservation;
import java.util.Arrays;
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
                Arguments.of(24, DiscountEvent.SPECIAL_EVENT, true),
                Arguments.of(23, DiscountEvent.CHRISTMAS_EVENT, true),
                Arguments.of(23, DiscountEvent.PROMOTION_EVENT, true),

                Arguments.of(22, DiscountEvent.WEEKDAY_EVENT, false),
                Arguments.of(22, DiscountEvent.WEEKEND_EVENT, true),
                Arguments.of(22, DiscountEvent.SPECIAL_EVENT, false),
                Arguments.of(21, DiscountEvent.CHRISTMAS_EVENT, true),
                Arguments.of(20, DiscountEvent.PROMOTION_EVENT, true)
        );

    }

    @ParameterizedTest(name = " 날짜 : {0} 이벤트 : {1} 결과 : {2} ")
    @MethodSource("dayAndEvent")
    void 날짜에_적용되는_이벤트_확인(int dayValue, DiscountEvent discountEvent, Boolean validOrUnvalid) {
        Day day = Day.from(dayValue);
        AcceptedOrders acceptedOrders = AcceptedOrders.from(Arrays.asList(Order.from("양송이스프-2"),
                Order.from("티본스테이크-3")));

        EventReservation eventReservation = EventReservation.of(day, acceptedOrders);
        boolean isValid = discountEvent.isValidEvent(eventReservation);

        Assertions.assertThat(isValid).isEqualTo(validOrUnvalid);
    }
}
