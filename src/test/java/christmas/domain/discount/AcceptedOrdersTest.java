package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import christmas.domain.menu.MenuType;
import christmas.domain.order.AcceptedOrders;
import christmas.domain.order.Order;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AcceptedOrdersTest {
    @Test
    void 특정_메뉴_개수_확인() {
        AcceptedOrders acceptedOrders = AcceptedOrders.from(Arrays.asList(Order.from("양송이스프-2"),
                Order.from("티본스테이크-3")));

        int specificMenuQuantity = acceptedOrders.getSpecificMenuQuantity(MenuType.MAIN_MENU);
        assertThat(specificMenuQuantity).isEqualTo(3);
    }

    private static Stream<Arguments> generateMenusAndTotalPrice() {
        return Stream.of(
                Arguments.of(Arrays.asList(Order.from("제로콜라-1"), Order.from("타파스-4")), 25000),
                Arguments.of(Arrays.asList(Order.from("제로콜라-1"), Order.from("양송이스프-1")), 9000),
                Arguments.of(Arrays.asList(Order.from("샴페인-3"), Order.from("아이스크림-2"),
                        Order.from("시저샐러드-2")), 101000)
        );
    }

    @ParameterizedTest
    @MethodSource("generateMenusAndTotalPrice")
    void 할인_전_총주문_금액_확인(List<Order> orders, int result){
        AcceptedOrders acceptedOrders = AcceptedOrders.from(orders);

        int totalPriceWithoutDiscount = acceptedOrders.checkTotalPriceWithoutDiscount();

        Assertions.assertThat(totalPriceWithoutDiscount).isEqualTo(result);
    }

    @Nested
    class validOrders {
        private static Stream<Arguments> generateNonDuplicatedMenus() {
            return Stream.of(
                    Arguments.of(Arrays.asList(Order.from("제로콜라-1"), Order.from("타파스-4"))),
                    Arguments.of(Arrays.asList(Order.from("제로콜라-1"), Order.from("양송이스프-1"))),
                    Arguments.of(Arrays.asList(Order.from("샴페인-3"), Order.from("아이스크림-2")))
            );
        }

        @ParameterizedTest()
        @MethodSource("generateNonDuplicatedMenus")
        void 중복된_메뉴가_아니라면_성공(List<Order> notDuplicated) {
            Assertions.assertThatCode(() ->
                            AcceptedOrders.from(notDuplicated)
                    ).doesNotThrowAnyException();
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
    }

    @Nested
    class unvalidOrders {
        private static Stream<Arguments> generateDuplicatedMenus() {
            return Stream.of(
                    Arguments.of(Arrays.asList(Order.from("제로콜라-1"), Order.from("제로콜라-4"))),
                    Arguments.of(Arrays.asList(Order.from("타파스-1"), Order.from("타파스-1"))),
                    Arguments.of(Arrays.asList(Order.from("티본스테이크-3"), Order.from("타파스-2"),
                            Order.from("타파스-3")))
            );
        }

        @ParameterizedTest()
        @MethodSource("generateDuplicatedMenus")
        void 중복된_메뉴가_존재하면_예외(List<Order> duplicatedMenus) {
            Assertions.assertThatCode(() ->
                            AcceptedOrders.from(duplicatedMenus)
                    ).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("유효하지 않은 주문입니다");
        }

        private static Stream<Arguments> generateOnlyDrinks() {
            return Stream.of(
                    Arguments.of(Arrays.asList(Order.from("제로콜라-1"), Order.from("샴페인-4"))),
                    Arguments.of(Arrays.asList(Order.from("제로콜라-1"), Order.from("레드와인-1"))),
                    Arguments.of(Arrays.asList(Order.from("샴페인-3"), Order.from("레드와인-2")))
            );
        }

        @ParameterizedTest()
        @MethodSource("generateOnlyDrinks")
        void 음료만_주문하면_예외(List<Order> onlyDrinks) {
            Assertions.assertThatCode(() ->
                            AcceptedOrders.from(onlyDrinks)
                    ).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("유효하지 않은 주문입니다");
        }

        private static Stream<Arguments> generateOverMaximumMenus() {
            return Stream.of(
                    Arguments.of(Arrays.asList(Order.from("제로콜라-6"), Order.from("샴페인-15"))),
                    Arguments.of(Arrays.asList(Order.from("티본스테이크-13"), Order.from("샴페인-4"), Order.from("타파스-5"))),
                    Arguments.of(Arrays.asList(Order.from("제로콜라-21")))
            );
        }

        @ParameterizedTest()
        @MethodSource("generateOverMaximumMenus")
        void 최대_메뉴_개수_20개룰_넘으면_예외(List<Order> overMaximumMenus) {
            Assertions.assertThatCode(() ->
                            AcceptedOrders.from(overMaximumMenus)
                    ).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("유효하지 않은 주문입니다");
        }
    }
}
