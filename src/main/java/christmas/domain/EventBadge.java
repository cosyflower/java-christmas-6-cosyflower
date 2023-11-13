package christmas.domain;

import christmas.domain.receipt.TotalPrice;
import christmas.domain.util.Constants;
import christmas.domain.discount.DiscountPrice;
import java.util.Arrays;
import java.util.function.Predicate;

public enum EventBadge {
    NOTHINIG("없음", (price) -> price.isBetweenPrice(Constants.INIT_VALUE, Constants.BADGE_FIRST_CRITERA)),
    STAR("별", (price) -> price.isBetweenPrice(Constants.BADGE_FIRST_CRITERA, Constants.BADGE_MIDDEL_CRITERA)),
    TREE("트리", (price) -> price.isBetweenPrice(Constants.BADGE_MIDDEL_CRITERA, Constants.BADGE_LAST_CRITERA)),
    SANTA("산타", (price) -> price.isBetweenPrice(Constants.BADGE_LAST_CRITERA, Integer.MAX_VALUE))
    ;

    private final String description;
    private final Predicate<TotalPrice> discountPriceCriteria; // TotalPrice로 변경하기


    EventBadge(String description,
               Predicate<TotalPrice> discountPriceCriteria) {
        this.description = description;
        this.discountPriceCriteria = discountPriceCriteria;
    }

    public static EventBadge findEventBadgeByDiscountPrice(TotalPrice totalPrice) {
        // 범위에 만족하면
        // 해당 배지를 반환한다
        return Arrays.stream(values())
                .filter((badge) -> badge.discountPriceCriteria.test(totalPrice))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public String getDescription() {
        return description;
    }
}
