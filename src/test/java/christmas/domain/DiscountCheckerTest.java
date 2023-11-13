package christmas.domain;

import static christmas.domain.discount.DiscountType.CHRISTMAS_DISCOUNT;
import static christmas.domain.discount.DiscountType.PROMOTION_DISCOUNT;
import static christmas.domain.discount.DiscountType.SPECIAL_DISCOUNT;
import static christmas.domain.discount.DiscountType.WEEKDAY_DISCOUNT;
import static christmas.domain.discount.DiscountType.WEEKEND_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.discount.DiscountChecker;
import christmas.domain.discount.DiscountPrice;
import christmas.domain.discount.DiscountType;
import christmas.domain.order.AcceptedOrders;
import christmas.domain.order.Order;
import christmas.domain.receipt.EventStatus;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

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
                Arrays.asList(Order.from("바베큐립-3"), Order.from("초코케이크-1")));
        EventReservation eventReservation = EventReservation.of(Day.from(day), acceptedOrders);

        List<DiscountType> validDiscountTypes = DiscountChecker.checkValidDiscountTypes(eventReservation);
        
        assertThat(validDiscountTypes).containsExactly(discountTypes);
    }

    private static Stream<Arguments> generateOrderWithoutPromotion() {
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
    @MethodSource("generateOrderWithoutPromotion")
    void 프로모션_없는_경우_검증(int day, DiscountType... discountTypes) {
        AcceptedOrders acceptedOrders = AcceptedOrders.from(
                Arrays.asList(Order.from("양송이스프-1")));
        EventReservation eventReservation = EventReservation.of(Day.from(day), acceptedOrders);

        List<DiscountType> validDiscountTypes = DiscountChecker.checkValidDiscountTypes(eventReservation);
        assertThat(validDiscountTypes).containsExactly(discountTypes);
    }

    @ParameterizedTest
    @ValueSource(ints = {23, 24, 25, 28})
    void 포로모션_있는_할인별_금액_확인(int day) {
        AcceptedOrders acceptedOrders = AcceptedOrders.from(
                Arrays.asList(Order.from("티본스테이크-2"), Order.from("초코케이크-1")));
        EventReservation eventReservation = EventReservation.of(Day.from(day), acceptedOrders);

        EventStatus allDiscountPrice = DiscountChecker.findAllDiscountPrice(eventReservation);
        Map<DiscountType, DiscountPrice> eventStatus = allDiscountPrice.getEventStatus();

        for (DiscountType discountType : eventStatus.keySet()) {
            System.out.println("discountType = " + discountType);
            System.out.println(eventStatus.get(discountType).getDiscountPriceValue());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {23, 24, 25, 28})
    void 프로모션_없이_할인별_금액_확인(int day) {
        AcceptedOrders acceptedOrders = AcceptedOrders.from(
                Arrays.asList(Order.from("티본스테이크-2"), Order.from("아이스크림-1")));
        EventReservation eventReservation = EventReservation.of(Day.from(day), acceptedOrders);

        EventStatus allDiscountPrice = DiscountChecker.findAllDiscountPrice(eventReservation);
        Map<DiscountType, DiscountPrice> eventStatus = allDiscountPrice.getEventStatus();

        for (DiscountType discountType : eventStatus.keySet()) {
            System.out.println("discountType = " + discountType);
            System.out.println(eventStatus.get(discountType).getDiscountPriceValue());
        }
    }
}
