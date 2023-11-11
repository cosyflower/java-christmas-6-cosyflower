package christmas.receipt;

import static christmas.domain.discount.DiscountType.CHRISTMAS_DISCOUNT;
import static christmas.domain.discount.DiscountType.SPECIAL_DISCOUNT;
import static christmas.domain.discount.DiscountType.WEEKDAY_DISCOUNT;
import static christmas.domain.discount.DiscountType.checkValidDiscountType;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Day;
import christmas.domain.discount.DiscountType;
import christmas.domain.order.AcceptedOrders;
import christmas.domain.order.Order;
import christmas.domain.receipt.AcceptedEvents;
import christmas.domain.receipt.Receipt;
import christmas.domain.receipt.TotalPrice;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class ReceiptTest {
//    Receipt의 TotalPrice를 확인한다 (Day, 총 주문 금액 정보가 필요하다)
//    1만원보다 적으면 AcceptedOrders를 초기화 해야 한다
    @Test
    void 만원_이상이면_이벤트_유효() {
        Day createdDay = Day.from(25);
        AcceptedOrders acceptedOrders = AcceptedOrders.from(
                Arrays.asList(Order.from("양송이스프-2")));

        AcceptedEvents acceptedEvents = AcceptedEvents.of(
                checkValidDiscountType(createdDay), TotalPrice.from(acceptedOrders.checkTotalPriceWithoutDiscount()));

        Receipt receipt = Receipt.of(acceptedEvents, acceptedOrders);
        assertThat(receipt.getAcceptedEvents().getDiscountTypes()).containsExactly(WEEKDAY_DISCOUNT,
                CHRISTMAS_DISCOUNT, SPECIAL_DISCOUNT);
    }

    @Test
    void 만원_보다_적으면_이벤트_없음() {
        Day createdDay = Day.from(25);
        AcceptedOrders acceptedOrders = AcceptedOrders.from(
                Arrays.asList(Order.from("타파스-1")));
        AcceptedEvents acceptedEvents = AcceptedEvents.of(
                checkValidDiscountType(createdDay), TotalPrice.from(acceptedOrders.checkTotalPriceWithoutDiscount()));

        Receipt receipt = Receipt.of(acceptedEvents, acceptedOrders);
        assertThat(receipt.getAcceptedEvents().getDiscountTypes()).containsExactly();
    }
}
