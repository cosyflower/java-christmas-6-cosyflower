package christmas;

import static christmas.domain.discount.DiscountType.CHRISTMAS_DISCOUNT;
import static christmas.domain.discount.DiscountType.PROMOTION_DISCOUNT;
import static christmas.domain.discount.DiscountType.SPECIAL_DISCOUNT;
import static christmas.domain.discount.DiscountType.WEEKDAY_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Day;
import christmas.domain.receipt.AcceptedEvents;
import christmas.domain.discount.DiscountType;
import christmas.domain.receipt.TotalPrice;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PromotionEventTest {
    //    - 1일 ~ 31일까지 진행되는 증정 이벤트의 조건을 충족하면 샴페인을 증정한다
    //    - 할인 전 최종 금액을 테스트 인자로 전달한다 (인자 값)
    //    - 할인 받기 전 총 주문 금액이 120000원 이상이 경우
    //    - 샴페인 (25,000원) 을 증정한다

    // 증정 이벤트는 DiscountType PROMOTION_EVENT
    // 할인 전 금액
    // 적용 가능한 이벤트
    // 위 둘을 저장하는 Receipt (가능 이벤트, 금액)
    @ParameterizedTest
    @ValueSource(ints = {120000, 121000})
    void 적정_금액_이상이면_프로모션_적용(int result) {
        Day everyDay = Day.from(24);
        List<DiscountType> discountTypes = DiscountType.checkValidDiscountType(everyDay);

        TotalPrice totalPrice = TotalPrice.from(result);
        AcceptedEvents acceptedEvents = AcceptedEvents.from(discountTypes, totalPrice);

        assertThat(acceptedEvents.getAcceptedEvents()).containsExactly(WEEKDAY_DISCOUNT, CHRISTMAS_DISCOUNT,
                PROMOTION_DISCOUNT, SPECIAL_DISCOUNT);
    }

    @ParameterizedTest
    @ValueSource(ints = {119999, 10000})
    void 적정_금액_보다_낮으면_프로모션_적용하지_않는다(int result) {
        Day everyDay = Day.from(24);
        List<DiscountType> discountTypes = DiscountType.checkValidDiscountType(everyDay);

        TotalPrice totalPrice = TotalPrice.from(result);
        AcceptedEvents acceptedEvents = AcceptedEvents.from(discountTypes, totalPrice);

        assertThat(acceptedEvents.getAcceptedEvents()).containsExactly(WEEKDAY_DISCOUNT, CHRISTMAS_DISCOUNT,
                SPECIAL_DISCOUNT);
    }
}
