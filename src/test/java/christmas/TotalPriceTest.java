package christmas;

import christmas.domain.receipt.TotalPrice;
import christmas.domain.order.AcceptedOrders;
import christmas.domain.order.Order;
import christmas.domain.util.Mapper;
import christmas.dto.OrderDTO;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class TotalPriceTest {
    private AcceptedOrders generateAcceptedOrders(String input) {
        OrderDTO orderDTO = new OrderDTO(input);
        AcceptedOrders acceptedOrders = Mapper.toMenuType(orderDTO);
        return acceptedOrders;
    }

    @ParameterizedTest
    @CsvSource(value = {"양송이스프-2,티본스테이크-3:177000"}, delimiter = ':')
    void 할인_적용_전_전체_가격_확인하기(String order, int total) {
        AcceptedOrders acceptedOrders = generateAcceptedOrders(order);
        Assertions.assertThat(acceptedOrders.getAcceptedOrders()).containsExactly(
                Order.from("양송이스프-2"), Order.from("티본스테이크-3")
        );

        TotalPrice totalPrice = TotalPrice.from(acceptedOrders.checkTotalPriceWithoutDiscount());
        Assertions.assertThat(totalPrice.getTotalPriceValue()).isEqualTo(total);
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
