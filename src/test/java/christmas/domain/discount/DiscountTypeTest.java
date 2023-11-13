package christmas.domain.discount;

import christmas.domain.order.AcceptedOrders;
import christmas.domain.order.Order;
import christmas.domain.reservation.Day;
import christmas.domain.reservation.EventReservation;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountTypeTest {
    @Test
    @DisplayName("Day에 적용 가능한 이벤트 리스트 확인하기")
    void findValidDiscountType() {
        Day day = Day.from(23);
        AcceptedOrders acceptedOrders = AcceptedOrders.from(Arrays.asList(Order.from("양송이스프-2"),
                Order.from("티본스테이크-3")));

        EventReservation eventReservation = EventReservation.of(day, acceptedOrders);
        List<DiscountType> discountTypes = DiscountChecker.checkValidDiscountTypes(eventReservation);

        Assertions.assertThat(discountTypes).containsExactly(DiscountType.WEEKEND_DISCOUNT,
                DiscountType.CHRISTMAS_DISCOUNT, DiscountType.PROMOTION_DISCOUNT);
    }
}
