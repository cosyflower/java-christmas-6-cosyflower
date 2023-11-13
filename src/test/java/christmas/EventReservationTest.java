package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Day;
import christmas.domain.EventReservation;
import christmas.domain.menu.MenuType;
import christmas.domain.order.AcceptedOrders;
import christmas.domain.order.Order;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EventReservationTest {
    private static Stream<Arguments> generateMainMenus() {
        return Stream.of(
                Arguments.of(Arrays.asList(Order.from("양송이스프-2"), Order.from("티본스테이크-3")), 3),
                Arguments.of(Arrays.asList(Order.from("양송이스프-14"), Order.from("티본스테이크-3")), 3),
                Arguments.of(Arrays.asList(Order.from("양송이스프-1"), Order.from("티본스테이크-1")), 1)
        );
    }

    @ParameterizedTest
    @MethodSource("generateMainMenus")
    void 메인_메뉴_전체_개수_검증(List<Order> orders, int expectedTotal) {
        Day day = Day.from(25);
        AcceptedOrders acceptedOrders = AcceptedOrders.from(orders);
        EventReservation eventReservation = EventReservation.of(day, acceptedOrders);

        int specificMenuTypeTotal = eventReservation.getSpecificMenuTypeTotal(MenuType.MAIN_MENU);

        assertThat(specificMenuTypeTotal).isEqualTo(expectedTotal);
    }

    private static Stream<Arguments> generateOrdersAndCriteria() {
        return Stream.of(
                Arguments.of(Arrays.asList(Order.from("양송이스프-2"), Order.from("티본스테이크-3")), 176999, true),
                Arguments.of(Arrays.asList(Order.from("양송이스프-14"), Order.from("티본스테이크-3")), 177000, true),
                Arguments.of(Arrays.asList(Order.from("양송이스프-1"), Order.from("티본스테이크-1")), 177001, false)
        );
    }

    @ParameterizedTest
    @MethodSource("generateOrdersAndCriteria")
    void 기준을_넘는지_넘지_않는지_검증(List<Order> orders, int criteria, boolean expectedResult) {
        Day day = Day.from(25);
        AcceptedOrders acceptedOrders = AcceptedOrders.from(orders);
        EventReservation eventReservation = EventReservation.of(day, acceptedOrders);

        boolean isPromoted = eventReservation.checkPromotion(criteria);

        Assertions.assertThat(isPromoted).isEqualTo(expectedResult);
    }
}
