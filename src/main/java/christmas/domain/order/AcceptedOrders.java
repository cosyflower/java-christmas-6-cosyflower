package christmas.domain.order;

import christmas.domain.menu.MenuProduct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AcceptedOrders {
    private final List<Order> acceptedOrders;

    private AcceptedOrders(List<Order> acceptedOrders) {
        validate(acceptedOrders);
        this.acceptedOrders = new ArrayList<>(acceptedOrders);
    }

    private void validate(List<Order> acceptedOrders) {
        isDuplicated(acceptedOrders);
    }

    private void isDuplicated(List<Order> acceptedOrders) {
        Set<MenuProduct> menuProducts = new HashSet<>();
        acceptedOrders.stream()
                .forEach((order) -> menuProducts.add(order.getMenuProduct()));
        if (acceptedOrders.size() != menuProducts.size()) {
            throw new IllegalArgumentException("(중복된 주문 메뉴) 유효하지 않는 주문입니다");
        }
    }

    public static AcceptedOrders from(List<Order> acceptedOrders) {
        return new AcceptedOrders(acceptedOrders);
    }

    public int checkTotalPriceWithoutDiscount() {
        // 할인 전 총 결제 금액
        return acceptedOrders.stream()
                .mapToInt(Order::getEachOrderTotalPrice)
                .sum();
    }

    public List<Order> getAcceptedOrders() {
        return Collections.unmodifiableList(acceptedOrders);
    }
}
