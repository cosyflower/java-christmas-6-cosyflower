package christmas.domain;

import christmas.discount.DiscountPrice;
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

    @ParameterizedTest(name = "할인 적용 전 총 구매 금액 : {0} 이벤트 배지 결과 : {1}")
    @MethodSource("totalPriceAndBadge")
    void createEventBadge(int totalDiscountPrice, EventBadge eventBadge) {
        DiscountPrice discountPrice = DiscountPrice.from(totalDiscountPrice);
        EventBadge eventBadgeByDiscountPrice = EventBadge.findEventBadgeByDiscountPrice(discountPrice);

        Assertions.assertThat(eventBadgeByDiscountPrice).isEqualTo(eventBadge);
    }

}
