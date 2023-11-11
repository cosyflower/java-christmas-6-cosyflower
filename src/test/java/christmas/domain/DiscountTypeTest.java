package christmas.domain;

import christmas.domain.discount.DiscountType;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountTypeTest {
    @Test
    @DisplayName("Day에 적용 가능한 이벤트 리스트 확인하기")
    void findValidDiscountType() {
        Day day = Day.from(25);

        List<DiscountType> discountTypes = DiscountType.checkValidDiscountType(day);

        Assertions.assertThat(discountTypes).containsExactly(DiscountType.WEEKDAY_DISCOUNT,
                DiscountType.CHRISTMAS_DISCOUNT, DiscountType.PROMOTION_DISCOUNT, DiscountType.SPECIAL_DISCOUNT);
    }
}