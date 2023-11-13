package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        assertThat(specificMenuQuantity).isEqualTo(3);
        // Assertions 추가
    }

    @Test
    void 음료만_주문하지_않으면_성공() {
        assertThatCode(
                () -> AcceptedOrders.from(Arrays.asList(Order.from("제로콜라-17"), Order.from("티본스테이크-3")))
        ).doesNotThrowAnyException();
    }

    @Test
    void 음식_메뉴_20개_이내면_성공() {
        assertThatCode(
                () -> AcceptedOrders.from(Arrays.asList(Order.from("제로콜라-17"), Order.from("티본스테이크-3")))
        ).doesNotThrowAnyException();
    }

    @Test
    void 음료만_주문하면_예외() {
        assertThatThrownBy(
                        () -> AcceptedOrders.from(Arrays.asList(Order.from("제로콜라-2"), Order.from("샴페인-2")))
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음료만 주문");
    }

    @Test
    void 음식_메뉴_20개_넘어가면_예외() {
        assertThatThrownBy(
                        () -> AcceptedOrders.from(Arrays.asList(Order.from("제로콜라-17"), Order.from("티본스테이크-4")))
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("20개를 넘음");
    }
}
