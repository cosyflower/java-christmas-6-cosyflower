package christmas.domain.discount;

import static christmas.domain.discount.DiscountType.CHRISTMAS_DISCOUNT;
import static christmas.domain.discount.DiscountType.PROMOTION_DISCOUNT;
import static christmas.domain.discount.DiscountType.SPECIAL_DISCOUNT;
import static christmas.domain.discount.DiscountType.WEEKDAY_DISCOUNT;
import static christmas.domain.discount.DiscountType.WEEKEND_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.AcceptedOrders;
import christmas.domain.order.Order;
import christmas.domain.receipt.EventStatus;
import christmas.domain.reservation.Day;
import christmas.domain.reservation.EventReservation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DiscountCheckerTest {
    private AcceptedOrders acceptedOrders;
    private EventReservation eventReservation;

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
    void 날짜에_맞는_이벤트_검증(int day, DiscountType... discountTypes) {
        acceptedOrders = AcceptedOrders.from(Arrays.asList(Order.from("바비큐립-3"), Order.from("초코케이크-1")));
        eventReservation = EventReservation.of(Day.from(day), acceptedOrders);

        List<DiscountType> validDiscountTypes = DiscountChecker.checkValidDiscountTypes(eventReservation);

        assertThat(validDiscountTypes).containsExactly(discountTypes);
    }

    @DisplayName("프로모션 여부 관련 이벤트")
    @Nested
    class PromotionChecker {
        private static Stream<Arguments> generateMenuWithPromotion() {
            return Stream.of(
                    Arguments.of(Arrays.asList(Order.from("티본스테이크-2"), Order.from("초코케이크-1")), 23),
                    Arguments.of(Arrays.asList(Order.from("크리스마스파스타-5")), 24),
                    Arguments.of(Arrays.asList(Order.from("바비큐립-1"), Order.from("해산물파스타-2")), 25)
            );
        }

        @ParameterizedTest
        @MethodSource("generateMenuWithPromotion")
        void 포로모션_할인이_적용된_경우(List<Order> orders, int day) {
            acceptedOrders = AcceptedOrders.from(orders);
            eventReservation = EventReservation.of(Day.from(day), acceptedOrders);

            EventStatus allDiscountPrice = DiscountChecker.findAllDiscountPrice(eventReservation);
            Map<DiscountType, DiscountPrice> eventStatus = allDiscountPrice.getEventStatus();

            assertThat(eventStatus.get(PROMOTION_DISCOUNT).getDiscountPriceValue()).isEqualTo(25000);
        }

        private static Stream<Arguments> generateMenuWithoutPromotion() {
            return Stream.of(
                    Arguments.of(Arrays.asList(Order.from("티본스테이크-2"), Order.from("아이스크림-1")), 23),
                    Arguments.of(Arrays.asList(Order.from("크리스마스파스타-3")), 24),
                    Arguments.of(Arrays.asList(Order.from("바비큐립-1"), Order.from("해산물파스타-1")), 25)
            );
        }

        @ParameterizedTest
        @MethodSource("generateMenuWithoutPromotion")
        void 프로모션_할인이_적용되지_않은_경우(List<Order> orders, int day) {
            acceptedOrders = AcceptedOrders.from(orders);
            eventReservation = EventReservation.of(Day.from(day), acceptedOrders);

            EventStatus allDiscountPrice = DiscountChecker.findAllDiscountPrice(eventReservation);
            Map<DiscountType, DiscountPrice> eventStatus = allDiscountPrice.getEventStatus();

            assertThat(eventStatus.get(PROMOTION_DISCOUNT).getDiscountPriceValue()).isEqualTo(0);
        }
    }

    private static Stream<Arguments> generateMenuWithoutDiscount(){
        return Stream.of(
                Arguments.of(Arrays.asList(Order.from("아이스크림-1")),2),
                Arguments.of(Arrays.asList(Order.from("아이스크림-1"), Order.from("제로콜라-1")), 3),
                Arguments.of(Arrays.asList(Order.from("시저샐러드-1")), 4)
        );
    }

    @ParameterizedTest
    @MethodSource("generateMenuWithoutDiscount")
    void 이벤트를_적용하지_못하는_금액_확인(List<Order> orders, int day) {
        acceptedOrders = AcceptedOrders.from(orders);
        eventReservation = EventReservation.of(Day.from(day), acceptedOrders);

        EventStatus allDiscountPrice = DiscountChecker.findAllDiscountPrice(eventReservation);
        Map<DiscountType, DiscountPrice> eventStatus = allDiscountPrice.getEventStatus();

        for (DiscountType discountType : eventStatus.keySet()) {
           assertThat(eventStatus.get(discountType).getDiscountPriceValue()).isEqualTo(0);
        }
    }

    @Test
    void 혜택_금액_그리고_실제_할인_금액_확인() {
        acceptedOrders = AcceptedOrders.from(Arrays.asList(Order.from("양송이스프-3"),
                Order.from("티본스테이크-2"), Order.from("아이스크림-1")));
        eventReservation = EventReservation.of(Day.from(25), acceptedOrders);
        EventStatus eventStatus = DiscountChecker.findAllDiscountPrice(eventReservation);

        int actualDiscountAmount = eventStatus.getActualDiscountAmount();
        int benefitAmount = eventStatus.getBenefitAmount();

        assertThat(actualDiscountAmount).isEqualTo(6423);
        assertThat(benefitAmount).isEqualTo(31423);
    }
}
