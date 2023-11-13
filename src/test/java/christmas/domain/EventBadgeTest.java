package christmas.domain;

import christmas.domain.receipt.TotalPrice;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EventBadgeTest {
    private static Stream<Arguments> totalPriceAndBadge() {
        return Stream.of(
                Arguments.of(5000, EventBadge.STAR),
                Arguments.of(10000, EventBadge.TREE),
                Arguments.of(20000, EventBadge.SANTA)
        );
    }

    @ParameterizedTest(name = "혜택 금액 : {0} 이벤트 배지 결과 : {1}")
    @MethodSource("totalPriceAndBadge")
    void createEventBadge(int benefitAmount, EventBadge eventBadge) {
        TotalPrice totalPrice = TotalPrice.from(benefitAmount);
        EventBadge eventBadgeByDiscountPrice = EventBadge.findEventBadgeByDiscountPrice(totalPrice);

        Assertions.assertThat(eventBadgeByDiscountPrice).isEqualTo(eventBadge);
    }

}
