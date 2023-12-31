package christmas.util;

import christmas.domain.order.AcceptedOrders;
import christmas.domain.order.Order;
import christmas.domain.reservation.Day;
import christmas.dto.DayDTO;
import christmas.dto.OrderDTO;
import java.util.Arrays;
import java.util.List;

public class Mapper {
    private static final String ACCEPTED_ORDERS_SEPARATOR = ",";

    public static AcceptedOrders toAcceptedOrders(OrderDTO orderDTO) {
        String[] splitOrders = orderDTO.getMenuAndNumber().split(ACCEPTED_ORDERS_SEPARATOR);
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
