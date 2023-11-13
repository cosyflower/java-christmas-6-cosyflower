package christmas.dto;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class DayDTOTest {
    @Nested
    @DisplayName(value = "예외가 발생하는 상황")
    class UnvalidDateDto {
        @ParameterizedTest
        @NullAndEmptySource
        void 아무것도_입력하지_않으면_예외(String nullAndEmpty) {
            Assertions.assertThatThrownBy(() -> new DayDTO(nullAndEmpty))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("아무것도");
        }

        private static Stream<Arguments> generateNonNumber() {
            return Stream.of(
                    Arguments.of("abc"),
                    Arguments.of("01A"),
                    Arguments.of("14_@")
            );
        }

        @ParameterizedTest
        @MethodSource("generateNonNumber")
        void 수가_아닌_문자를_입력하면_예외(String nonNumber) {
            Assertions.assertThatThrownBy(() -> new DayDTO(nonNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("유효하지 않은 날짜입니다.");
        }
    }

    @Nested
    @DisplayName("예외가 발생하지 않는 경우")
    class ValidDateDto {
        private static Stream<Arguments> generateNumber() {
            return Stream.of(
                    Arguments.of("12"),
                    Arguments.of("012"),
                    Arguments.of("120")
            );
        }

        @ParameterizedTest
        @MethodSource("generateNumber")
        void 수를_입력하면_성공(String number) {
            Assertions.assertThatCode(() -> new DayDTO(number))
                    .doesNotThrowAnyException();
        }
    }
}
