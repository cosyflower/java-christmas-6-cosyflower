package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MapperTest {
    @ParameterizedTest
    @ValueSource(strings = {"양송이스프-2", "티본스테이크-3"})
    void orderDTO_To_AcceptedOrders(String input) {
        OrderDTO orderDTO = new OrderDTO(input);
        AcceptedOrders acceptedOrders = Mapper.toMenuType(orderDTO.getMenuAndNumber());

        assertThat(acceptedOrders.getAcceptedOrders()).contains(Order.from(input));
    }
}
