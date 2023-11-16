package christmas.domain.receipt;

import christmas.domain.order.AcceptedOrders;
import christmas.dto.OrderDTO;
import christmas.util.Mapper;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
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

    public static class TotalPriceTest {
        private AcceptedOrders generateAcceptedOrders(String input) {
            OrderDTO orderDTO = new OrderDTO(input);
            AcceptedOrders acceptedOrders = Mapper.toAcceptedOrders(orderDTO);
            return acceptedOrders;
        }

        @ParameterizedTest
        @CsvSource(value = {"양송이스프-2,티본스테이크-3:177000"}, delimiter = ':')
        void 할인_적용_전_전체_가격_확인하기(String order, int total) {
            AcceptedOrders acceptedOrders = generateAcceptedOrders(order);

            int totalPrice = acceptedOrders.checkTotalPriceWithoutDiscount();
            Assertions.assertThat(totalPrice).isEqualTo(total);
        }

        private static Stream<Arguments> generateAcceptedOrderAndTotalPrice() {
            return Stream.of(
                    Arguments.of("양송이스프-2,티본스테이크-3", 177000),
                    Arguments.of("해산물파스타-1,크리스마스파스타-1", 60000),
                    Arguments.of("초코케이크-2,아이스크림-1", 35000)
            );
        }

        @ParameterizedTest
        @MethodSource("generateAcceptedOrderAndTotalPrice")
        void 복수_메뉴_주문_그리고_할인_전_금액_확인(String order, int total) {
            AcceptedOrders acceptedOrders = generateAcceptedOrders(order);
            int expectedResult = acceptedOrders.checkTotalPriceWithoutDiscount();

            Assertions.assertThat(expectedResult).isEqualTo(total);
        }
    }
}
