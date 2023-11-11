package christmas.domain.util;

import christmas.domain.order.AcceptedOrders;
import christmas.domain.order.Order;
import java.util.Arrays;
import java.util.List;

public class Mapper {
    // OrderDTO를 받아서 먼저 ',' 별로 구분 - Order 하나씩 형성 - AcceptedOrders 로
    public static AcceptedOrders toMenuType(String orderDTO) {
        String[] splitOrders = orderDTO.split(",");
        // 각각의 메뉴를 형성할 수 있도록 도와준다
        List<Order> orders = Arrays.stream(splitOrders)
                .map(order -> Order.from(order))
                .toList();
        return AcceptedOrders.from(orders);
    }

}
