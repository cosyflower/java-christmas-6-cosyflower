package christmas.domain.receipt;

import static christmas.util.Constants.INIT_VALUE;

import christmas.domain.discount.DiscountPrice;
import christmas.domain.discount.DiscountType;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class EventStatus {
    private final Map<DiscountType, DiscountPrice> eventStatus = new EnumMap<>(DiscountType.class);

    public EventStatus() {
        Arrays.stream(DiscountType.values())
                .forEach(discountType -> eventStatus.put(discountType, DiscountPrice.from(INIT_VALUE)));
    }

    public void addStatus(DiscountType discountType, DiscountPrice discountPrice) {
        eventStatus.replace(discountType, discountPrice);
    }

    public int getBenefitAmount() { // 혜택은 프로모션 포함함
        return eventStatus.entrySet()
                .stream()
                .mapToInt(entry -> entry.getValue().getDiscountPriceValue())
                .sum();
    }

    public int getActualDiscountAmount() { // 실제 할인은 프로모션 포함하지 않음
        return eventStatus.entrySet()
                .stream()
                .filter(entry -> entry.getKey() != DiscountType.PROMOTION_DISCOUNT)
                .mapToInt(entry -> entry.getValue().getDiscountPriceValue())
                .sum();
    }

    public Map<DiscountType, DiscountPrice> getEventStatus() {
        return Collections.unmodifiableMap(eventStatus);
    }

    public boolean hasDiscountPrice(DiscountType discountType) {
        return eventStatus.get(discountType).isValidDiscount();
    }

    public Map<DiscountType, DiscountPrice> removeNonAppliedDiscount() {
        Set<DiscountType> discountTypes = eventStatus.keySet();
        for (DiscountType discountType : discountTypes) {
            if (eventStatus.get(discountType).isUnvalidDiscount()) { // Refactor
                eventStatus.remove(discountType);
            }
        }
        return eventStatus;
    }
}
