package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DayTest {
    // 1- 31 이하인지 검증한다
    @ParameterizedTest
    @ValueSource(ints = {1,31})
    void 범위_내_숫자인_경우_성공(int validNumber) {
        Assertions.assertThatCode(() -> Day.from(validNumber))
                .doesNotThrowAnyException();
    }
}
