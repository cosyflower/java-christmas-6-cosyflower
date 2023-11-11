package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import christmas.dto.OrderDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderDTOTest {

    @Nested
    @DisplayName("예외가 발생하는 경우")
    class unvalidOrder {

        @ParameterizedTest
        @NullAndEmptySource
        void 아무것도_입력하지_않은_경우_예외(String unvalidOrder) {
            Assertions.assertThatThrownBy(() -> new OrderDTO(unvalidOrder))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("아무것도 입력하지");
        }
    }

    @Nested
    @DisplayName("예외가 발생하지 않는 경우")
    class validOrder {
        @ParameterizedTest
        @ValueSource(strings = {"양송이스프-2", "스테이크-3", "양송이스프-2,스테이크-3"})
        void 주문_형식에_맞게_입력하면_성공(String validOrder) {
            assertThatCode(() -> new OrderDTO(validOrder))
                    .doesNotThrowAnyException();
        }
    }

}
