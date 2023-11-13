package christmas.util;

import christmas.domain.order.AcceptedOrders;
import christmas.domain.order.Order;
import christmas.domain.reservation.Day;
import christmas.dto.DayDTO;
import christmas.dto.OrderDTO;
import java.util.Arrays;
import java.util.List;

public class Mapper {
    // OrderDTO를 받아서 먼저 ',' 별로 구분 - Order 하나씩 형성 - AcceptedOrders 로
    public static AcceptedOrders toMenuType(OrderDTO orderDTO) {
        String[] splitOrders = orderDTO.getMenuAndNumber().split(",");
        // 각각의 메뉴를 형성할 수 있도록 도와준다
        List<Order> orders = Arrays.stream(splitOrders)
                .map(order -> Order.from(order))
                .toList();
        return AcceptedOrders.from(orders);
    }

    public static Day toDay(DayDTO dayDTO) {
        String date = dayDTO.getDateDTO();
        return Day.from(Util.convertStringToInt(date));
    }
}
