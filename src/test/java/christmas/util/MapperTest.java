package christmas.util;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.AcceptedOrders;
import christmas.domain.order.Order;
import christmas.domain.reservation.Day;
import christmas.dto.DayDTO;
import christmas.dto.OrderDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MapperTest {
    @ParameterizedTest
    @ValueSource(strings = {"0", "32"})
    void 범위_외_숫자_문자열이면_예외(String day) {
        Assertions.assertThatCode(() -> {
                            DayDTO dayDTO = new DayDTO(day);
                            Day mappedDay = Mapper.toDay(dayDTO);
                        }
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 날짜입니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "31"})
    void 범위_내_숫자_문자열이면_성공(String day) {
        Assertions.assertThatCode(() -> {
            DayDTO dayDTO = new DayDTO(day);
            Day mappedDay = Mapper.toDay(dayDTO);

        }).doesNotThrowAnyException();
    }

    @Nested
    @DisplayName("단일 메뉴만 주문한 경우")
    class SingleMenu {
        @ParameterizedTest
        @ValueSource(strings = {"양송이스파게티-2", "와이본스테이크-3"})
        void 존재하지_않는_메뉴_예외(String notInMenu) {
            Assertions.assertThatCode(() -> {
                        OrderDTO orderDTO = new OrderDTO(notInMenu);
                        Mapper.toAcceptedOrders(orderDTO);
                    }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("유효하지 않은 주문입니다.");
        }

        @ParameterizedTest(name = "orderDTO에서 AcceptedOrders로 변환")
        @ValueSource(strings = {"양송이스프-2", "티본스테이크-3"})
        void orderDTO_To_AcceptedOrders(String input) {
            OrderDTO orderDTO = new OrderDTO(input);
            AcceptedOrders acceptedOrders = Mapper.toAcceptedOrders(orderDTO);

            assertThat(acceptedOrders.getAcceptedOrders()).contains(Order.from(input));
        }
    }

    @Nested
    @DisplayName("복수 메뉴 주문한 경우")
    class MultipleMenu {
        @ParameterizedTest
        @ValueSource(strings = {"양송이스프-2,양송이스프-3", "티본스테이크-3,티본스테이크-2"})
        void 중복하는_메뉴를_입력하면_예외(String duplicatedMenu) {
            Assertions.assertThatCode(() -> {
                        OrderDTO orderDTO = new OrderDTO(duplicatedMenu);
                        Mapper.toAcceptedOrders(orderDTO);
                    }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("유효하지 않은 주문입니다");
        }

        @ParameterizedTest
        @ValueSource(strings = {"양송이스프-2,시저샐러드-3", "티본스테이크-3,샴페인-2"})
        void 중복하는_메뉴를_입력하지_않으면_성공(String nonDuplicatedMenu) {
            Assertions.assertThatCode(() -> {
                        OrderDTO orderDTO = new OrderDTO(nonDuplicatedMenu);
                        Mapper.toAcceptedOrders(orderDTO);
                    }).doesNotThrowAnyException();
        }
    }
}
