package christmas.domain;

import christmas.Constants;
import christmas.domain.discount.DiscountPrice;
import java.util.Arrays;
import java.util.function.Predicate;

public enum EventBadge {
    STAR("별", (price) -> price.isBetweenPrice(Constants.BADGE_FIRST_CRITERA, Constants.BADGE_MIDDEL_CRITERA)),
    TREE("트리", (price) -> price.isBetweenPrice(Constants.BADGE_MIDDEL_CRITERA, Constants.BADGE_LAST_CRITERA)),
    SANTA("산타", (price) -> price.isBetweenPrice(Constants.BADGE_LAST_CRITERA, Integer.MAX_VALUE))
    ;

    private final String description;
    private final Predicate<DiscountPrice> discountPriceCriteria;

//    private static Map<EventBadge, Predicate<DiscountPrice>> eventBadgeDiscountPriceMap =
//            Collections.unmodifiableMap(Arrays.stream(values())
//                    .collect(Collectors.toMap(Function.identity(), (map) -> map.discountPriceCriteria))
//            );

    EventBadge(String description,
               Predicate<DiscountPrice> discountPriceCriteria) {
        this.description = description;
        this.discountPriceCriteria = discountPriceCriteria;
    }

    public static EventBadge findEventBadgeByDiscountPrice(DiscountPrice discountPrice) {
        // 범위에 만족하면
        // 해당 배지를 반환한다
        return Arrays.stream(values())
                .filter((badge) -> badge.discountPriceCriteria.test(discountPrice))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public String getDescription() {
        return description;
    }
}
