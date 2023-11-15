package christmas.domain.receipt;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.discount.DiscountPrice;
import christmas.domain.discount.DiscountType;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class EventStatusTest {
    @Test
    void 할인_금액이_0인_경우_삭제_검증(){
        EventStatus eventStatus = new EventStatus();
        Map<DiscountType, DiscountPrice> removedMap = eventStatus.removeNonAppliedDiscount();

        assertThat(removedMap.size()).isEqualTo(0);
    }
}
