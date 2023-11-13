package christmas.dto;

import static org.assertj.core.api.Assertions.assertThatCode;

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

        @ParameterizedTest(name = "$메뉴명-$메뉴 개수의 형식을 지키지 않은 경우")
        @ValueSource(strings = {"양송이스프2개", "티본스테이크 - 2개", "티본스테이크-2개"})
        void 형식에_맞지_않는_경우_예외(String nonPatternOrder) {
            Assertions.assertThatThrownBy(() -> new OrderDTO(nonPatternOrder))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("유효하지 않은 주문입니다.");
        }

        @ParameterizedTest(name = "형식에 맞게 입력했으나 복수의 메뉴를 주문시 구분자를 활용하지 않은 경우")
        @ValueSource(strings = {"양송이스프-2티본스테이크-3"})
        void 구분자가_없는_경우_예외(String nonSeparator) {
            Assertions.assertThatThrownBy(() -> new OrderDTO(nonSeparator))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("유효하지 않은 주문입니다.");
        }

        @ParameterizedTest(name = "형식에 맞게 입력했으나 복수의 메뉴를 주문시 정해진 구분자 콤마(',')가 아닌 다른 문자를 명시한 경우")
        @ValueSource(strings = {"양송이스프-2|스테이크-3", "스테이크-3:양송이스프-2", "양송이스프-2 스테이크-3"})
        void 주문_형식에_맞게_입력했으나_콤마를_사용하지_않으면_예외(String wrongSeparator) {
            Assertions.assertThatThrownBy(() -> new OrderDTO(wrongSeparator))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("유효하지 않은 주문입니다.");
        }
    }

    @Nested
    @DisplayName("예외가 발생하지 않는 경우")
    class validOrder {
        @ParameterizedTest
        @ValueSource(strings = {"양송이스프-2,티본스테이크-3", "양송이스프-2,양송이스프-2"})
        void 형식에_맞게_입력하면_성공(String validOrder) {
            assertThatCode(() -> new OrderDTO(validOrder))
                    .doesNotThrowAnyException();
        }
    }

}
