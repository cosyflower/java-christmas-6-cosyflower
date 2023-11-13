package christmas.domain.order;

import christmas.domain.menu.MenuProduct;
import christmas.domain.menu.MenuType;
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
        hasOnlyDrinks(acceptedOrders);
        hasOutOfMaximumOrders(acceptedOrders);
    }

    private void hasOnlyDrinks(List<Order> acceptedOrders) {
        if (acceptedOrders.stream().allMatch(order -> order.hasSameMenuType(MenuType.DRINK))) {
            throw new IllegalArgumentException("(음료만 주문) 유효하지 않는 주문입니다");
        }
    }

    private void hasOutOfMaximumOrders(List<Order> acceptedOrders) {
        if (acceptedOrders.stream()
                .mapToInt(order -> order.getMenuQuantity().getMenuQuantityValue())
                .sum() > 20) {
            throw new IllegalArgumentException("(최대 20개를 넘음) 유효하지 않는 주문입니다");
        }
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

    public int getSpecificMenuQuantity(MenuType menuType) {
        return acceptedOrders.stream()
                .filter(order -> order.hasSameMenuType(menuType))
                .mapToInt(order -> order.getMenuQuantity().getMenuQuantityValue())
                .sum();
    }

    public boolean canGetPromotion(int promotionCriteria) {
        if (checkTotalPriceWithoutDiscount() >= promotionCriteria) {
            return true;
        }
        return false;
    }
}
