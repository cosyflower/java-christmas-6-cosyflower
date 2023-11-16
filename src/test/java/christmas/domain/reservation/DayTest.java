package christmas.domain.reservation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DayTest {
    @ParameterizedTest
    @ValueSource(ints = {1,31})
    void 범위_내_숫자인_경우_성공(int validNumber) {
        Assertions.assertThatCode(() -> Day.from(validNumber))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0,32})
    void 범위_내_숫자가_아닌_경우_예외(int unvalidNumber) {
        Assertions.assertThatCode(() -> Day.from(unvalidNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 날짜입니다.");
    }
}
