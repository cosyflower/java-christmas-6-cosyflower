package christmas.domain;

import static christmas.domain.discount.DiscountType.CHRISTMAS_DISCOUNT;
import static christmas.domain.discount.DiscountType.PROMOTION_DISCOUNT;
import static christmas.domain.discount.DiscountType.SPECIAL_DISCOUNT;
import static christmas.domain.discount.DiscountType.WEEKDAY_DISCOUNT;
import static christmas.domain.discount.DiscountType.WEEKEND_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.discount.DiscountChecker;
import christmas.domain.discount.DiscountType;
import christmas.domain.order.AcceptedOrders;
import christmas.domain.order.Order;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DiscountCheckerTest {

    private static Stream<Arguments> generateOrdersAndDiscountTypes() {
        return Stream.of(
                Arguments.of(30, new DiscountType[]{WEEKEND_DISCOUNT, PROMOTION_DISCOUNT}),
                Arguments.of(24, new DiscountType[]{WEEKDAY_DISCOUNT, CHRISTMAS_DISCOUNT,
                        PROMOTION_DISCOUNT, SPECIAL_DISCOUNT}),
                Arguments.of(23, new DiscountType[]{WEEKEND_DISCOUNT, CHRISTMAS_DISCOUNT,
                        PROMOTION_DISCOUNT}),
                Arguments.of(21, new DiscountType[]{WEEKDAY_DISCOUNT, CHRISTMAS_DISCOUNT,
                        PROMOTION_DISCOUNT})
        );
    }

    @ParameterizedTest
    @MethodSource("generateOrdersAndDiscountTypes")
    void flow(int day, DiscountType... discountTypes) {
        AcceptedOrders acceptedOrders = AcceptedOrders.from(
                Arrays.asList(Order.from("바베큐립-3"), Order.from("시저샐러드-1")));
        EventReservation eventReservation = EventReservation.of(Day.from(day), acceptedOrders);

        List<DiscountType> validDiscountTypes = DiscountChecker.checkValidDiscountType(eventReservation);

        assertThat(validDiscountTypes).containsExactly(discountTypes);
    }
}
