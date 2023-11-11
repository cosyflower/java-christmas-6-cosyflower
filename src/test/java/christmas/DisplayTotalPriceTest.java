package christmas;

import christmas.domain.order.AcceptedOrders;
import christmas.domain.order.Order;
import christmas.domain.util.Mapper;
import christmas.dto.OrderDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DisplayTotalPriceTest {
    // 할인 받기 전 총 결제 내역을 확인한다
    // 주문 내역은 AcceptedOrders로 확인한다
    // Order은 MenuProduct Quantity로 구성
    // MenuPrice 정보 * Quantitiy, 이들의 합을 가지고 있는 값을 반환한다
    private AcceptedOrders generateAcceptedOrders(String input) {
        OrderDTO orderDTO = new OrderDTO(input);
        AcceptedOrders acceptedOrders = Mapper.toMenuType(orderDTO);
        return acceptedOrders;
    }

    @ParameterizedTest
    @CsvSource(value = {"양송이스프-2,티본스테이크-3:177000"}, delimiter = ':') // 통과한 상황
    void 할인_적용_전_전체_가격_확인하기(String order, int total) {
        AcceptedOrders acceptedOrders = generateAcceptedOrders(order);
        Assertions.assertThat(acceptedOrders.getAcceptedOrders()).containsExactly(
                Order.from("양송이스프-2"), Order.from("티본스테이크-3")
        );

        TotalPrice totalPrice = TotalPrice.from(acceptedOrders.checkTotalPriceWithoutDiscount());
        Assertions.assertThat(totalPrice.getTotalPrice()).isEqualTo(total);
    }



}
