package christmas.view;

import static christmas.util.Constants.INIT_VALUE;

import christmas.domain.discount.DiscountPrice;
import christmas.domain.discount.DiscountType;
import christmas.domain.order.AcceptedOrders;
import christmas.domain.order.Order;
import christmas.domain.receipt.EventBadge;
import christmas.domain.receipt.EventStatus;
import christmas.domain.receipt.TotalPrice;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final int NOTHING = 0;

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_SUFFIX = " 다시 입력해 주세요.";

    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_DESCRIPTION_MESSAGE = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    private static final String ACCEPTED_ORDERS_MESSAGE = "<주문 메뉴>";
    private static final String ACCEPTED_ORDERS_FORMAT = "%s %d%s\n";
    private static final String ACCEPTED_ORDERS_SUFFIX = "개";

    private static final String TOTAL_PRICE_WITHOUT_DISCOUNT_MESSAGE = "<할인 전 총주문 금액>";
    private static final String WITHOUT_DISCOUNT_FORMAT = "%,d원\n";

    private static final String PROMOTION_MESSAGE = "<증정 메뉴>";
    private static final String PROMOTION = "샴페인 1개";
    private static final String NON_APPLIED = "없음";

    private static final String BENEFIT_MESSAGE = "<혜택 내역>";
    private static final String BENEFIT_FORMAT = "%s: -%,d원\n";
    private static final String NON_APPLIED_BENEFIT_FORMAT = "%d원\n";
    private static final String APPLIED_BENEFIT_FORMAT = "-%,d원\n";

    private static final String TOTAL_BENEFIT_MESSAGE = "<총혜택 금액>";
    private static final String EVENT_BADGE_MESSAGE = "<12월 이벤트 배지>";

    private static final String TOTAL_PRICE_WITH_DISCOUNT_MESSAGE = "<할인 후 예상 결제 금액>";
    private static final String WITH_DISCOUNT_FORMAT = "%,d원\n";

    public void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message + ERROR_SUFFIX);
    }

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printEventDescription() {
        System.out.println(EVENT_DESCRIPTION_MESSAGE);
    }

    public void printNewLine() {
        System.out.println();
    }

    public void printAcceptedOrders(AcceptedOrders acceptedOrders) {
        System.out.println(ACCEPTED_ORDERS_MESSAGE);
        List<Order> orders = acceptedOrders.getAcceptedOrders();
        orders.stream()
                .forEach(order -> System.out.printf(ACCEPTED_ORDERS_FORMAT, order.getMenuNameValue(),
                        order.getMenuQuantityValue(), ACCEPTED_ORDERS_SUFFIX));
    }

    public void printTotalPrcieWithoutDiscount(int checkTotalPriceWithoutDiscount) {
        System.out.println(TOTAL_PRICE_WITHOUT_DISCOUNT_MESSAGE);
        System.out.printf(WITHOUT_DISCOUNT_FORMAT, checkTotalPriceWithoutDiscount);
    }

    public void printPromotion(EventStatus status) {
        System.out.println(PROMOTION_MESSAGE);
        if (status.hasDiscountPrice(DiscountType.PROMOTION_DISCOUNT)) {
            System.out.println(PROMOTION);
            return;
        }

        System.out.println(NON_APPLIED);
    }

    public void printBenefit(EventStatus eventStatus) {
        System.out.println(BENEFIT_MESSAGE);
        Map<DiscountType, DiscountPrice> statusMap = eventStatus.removeNonAppliedDiscount();
        if (statusMap.size() == NOTHING) {
            System.out.println(NON_APPLIED);
        }

        for (DiscountType discountType : statusMap.keySet()) {
            System.out.printf(BENEFIT_FORMAT, discountType.getDiscountEventNameValue(),
                    statusMap.get(discountType).getDiscountPriceValue());
        }
    }

    public void printTotalBenefit(EventStatus eventStatus) {
        System.out.println(TOTAL_BENEFIT_MESSAGE);
        int benefitAmount = eventStatus.getBenefitAmount();
        if (benefitAmount == INIT_VALUE) {
            System.out.printf(NON_APPLIED_BENEFIT_FORMAT, benefitAmount);
            return;
        }
        System.out.printf(APPLIED_BENEFIT_FORMAT, benefitAmount);
    }

    public void printTotalPriceWithDiscount(EventStatus eventStatus, AcceptedOrders acceptedOrders) {
        System.out.println(TOTAL_PRICE_WITH_DISCOUNT_MESSAGE);
        System.out.printf(WITH_DISCOUNT_FORMAT,
                acceptedOrders.checkTotalPriceWithoutDiscount() - eventStatus.getActualDiscountAmount());
    }

    public void printEventBadge(EventStatus eventStatus) {
        System.out.println(EVENT_BADGE_MESSAGE);
        EventBadge eventBadge = EventBadge.findEventBadgeByDiscountPrice(
                TotalPrice.from(eventStatus.getBenefitAmount()));
        System.out.println(eventBadge.getDescription());
    }
}
