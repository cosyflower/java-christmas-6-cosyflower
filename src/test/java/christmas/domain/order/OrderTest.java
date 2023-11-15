package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderTest {
    @Nested
    class unvalidOrder {
        @ParameterizedTest
        @ValueSource(strings = {"티본스테이-012", "초코이크-0"})
        void 존재하지_않는_메뉴면_예외(String input) {
            assertThatCode(() -> Order.from(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("유효하지 않은 주문입니다.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"티본스테이크-012", "초코케이크-0"})
        void 수량이_0으로_시작하면_예외(String input) {
            assertThatCode(() -> Order.from(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("유효하지 않은 주문입니다.");
        }
    }

    @Nested
    class validOrder {
        @ParameterizedTest
        @ValueSource(strings = {"티본스테이크-12", "초코케이크-5"})
        void 존재하는_메뉴면_성공(String input) {
            assertThatCode(() -> Order.from(input))
                    .doesNotThrowAnyException();
        }

        @ParameterizedTest
        @ValueSource(strings = {"티본스테이크-12", "초코케이크-4"})
        void 수량이_0으로_시작하지_않으면_성공(String input) {
            assertThatCode(() -> Order.from(input))
                    .doesNotThrowAnyException();
        }

    }
}
