package christmas.receipt;

import static christmas.domain.discount.DiscountType.CHRISTMAS_DISCOUNT;
import static christmas.domain.discount.DiscountType.SPECIAL_DISCOUNT;
import static christmas.domain.discount.DiscountType.WEEKDAY_DISCOUNT;
import static christmas.domain.discount.DiscountType.checkValidDiscountType;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Day;
import christmas.domain.discount.DiscountType;
import christmas.domain.receipt.AcceptedEvents;
import christmas.domain.receipt.Receipt;
import christmas.domain.receipt.TotalPrice;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ReceiptTest {
//    Receipt의 TotalPrice를 확인한다 (Day, 총 주문 금액 정보가 필요하다)
//    1만원보다 적으면 AcceptedOrders를 초기화 해야 한다
    @ParameterizedTest
    @CsvSource(value = {"10000:25", "10001:25"}, delimiter = ':')
    void 만원_이상이면_이벤트_유효(int price, int day) {
        Day createdDay = Day.from(day);
        TotalPrice totalPrice = TotalPrice.from(price);

        AcceptedEvents acceptedEvents = AcceptedEvents.of(
                checkValidDiscountType(createdDay), totalPrice);

        Receipt receipt = Receipt.of(totalPrice, acceptedEvents);
        assertThat(receipt.getAcceptedEvents().getDiscountTypes()).containsExactly(WEEKDAY_DISCOUNT,
                CHRISTMAS_DISCOUNT, SPECIAL_DISCOUNT);
    }

    @ParameterizedTest
    @CsvSource(value = {"9999:25", "9998:25"}, delimiter = ':')
    void 만원_보다_적으면_이벤트_없음(int price, int day) {
        Day createdDay = Day.from(day);
        TotalPrice totalPrice = TotalPrice.from(price);

        AcceptedEvents acceptedEvents = AcceptedEvents.of(
                checkValidDiscountType(createdDay), totalPrice);

        Receipt receipt = Receipt.of(totalPrice, acceptedEvents);
        for (DiscountType discountType : receipt.getAcceptedEvents().getDiscountTypes()) {
            System.out.println("discountType = " + discountType);
        }
        assertThat(receipt.getAcceptedEvents().getDiscountTypes()).containsExactly();
    }
}
