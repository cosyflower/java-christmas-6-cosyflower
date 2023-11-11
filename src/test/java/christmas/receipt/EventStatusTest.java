package christmas.receipt;

import static christmas.domain.discount.DiscountType.checkValidDiscountType;

import christmas.domain.Day;
import christmas.domain.discount.DiscountType;
import christmas.domain.receipt.AcceptedEvents;
import christmas.domain.receipt.Receipt;
import christmas.domain.receipt.TotalPrice;
import java.util.List;
import org.junit.jupiter.api.Test;

public class EventStatusTest {
    @Test
    void flow() {
        // DiscountEvent, 0원으로 모두 초기화 한 상황에서 진행한다
        // Day 정보 그리고 Receipt 정보가 필요하다
        Day day = Day.from(13);
        List<DiscountType> discountTypes = DiscountType.checkValidDiscountType(day);
        TotalPrice totalPrice = TotalPrice.from(30000);
        AcceptedEvents acceptedEvents = AcceptedEvents.of(
                checkValidDiscountType(day), totalPrice);

//        Receipt receipt = Receipt.of(totalPrice, acceptedEvents);
        // Receipt 주문도 가지고 있어야 한다
    }
}
