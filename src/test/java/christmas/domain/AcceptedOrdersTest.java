package christmas.domain;

import christmas.domain.menu.MenuType;
import christmas.domain.order.AcceptedOrders;
import christmas.domain.order.Order;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AcceptedOrdersTest {
    @Test
    @DisplayName("메인 메뉴 개수 확인")
    void 특정_메뉴_개수_확인() {
        AcceptedOrders acceptedOrders = AcceptedOrders.from(Arrays.asList(Order.from("양송이스프-2"),
                Order.from("티본스테이크-3")));

        int specificMenuQuantity = acceptedOrders.getSpecificMenuQuantity(MenuType.MAIN_MENU);// 티본만
        System.out.println("specificMenuQuantity = " + specificMenuQuantity);
    }
}
