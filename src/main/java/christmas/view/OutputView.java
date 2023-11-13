package christmas.view;

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
    public void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message + " 다시 입력해 주세요.");
    }

    public void printStartMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventDescription() {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printNewLine() {
        System.out.println();
    }

    public void printAcceptedOrders(AcceptedOrders acceptedOrders) {
        System.out.println("<주문 메뉴>");
        List<Order> orders = acceptedOrders.getAcceptedOrders();
        orders.stream()
                .forEach(order -> System.out.printf("%s %d%s\n", order.getMenuNameValue(),
                        order.getMenuQuantityValue(), "개"));
    }

    public void printTotalPrcieWithoutDiscount(int checkTotalPriceWithoutDiscount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%,d원\n", checkTotalPriceWithoutDiscount);
    }

    public void printPromotion(EventStatus status) {
        System.out.println("<증정 메뉴>");
        if (status.hasDiscountPrice(DiscountType.PROMOTION_DISCOUNT)) {
            System.out.println("샴페인 1개");
            return;
        }

        System.out.println("없음");
    }

    public void printBenefit(EventStatus eventStatus) {
        System.out.println("<혜택 내역>");
        Map<DiscountType, DiscountPrice> statusMap = eventStatus.removeNonAppliedDiscount();
        if (statusMap.size() == 0) {
            System.out.println("없음");
        }

        for (DiscountType discountType : statusMap.keySet()) {
            System.out.printf("%s: -%,d원\n", discountType.getDiscountEventNameValue(),
                    statusMap.get(discountType).getDiscountPriceValue());
        }
    }

    public void printTotalBenefit(EventStatus eventStatus) {
        System.out.println("<총혜택 금액>");
        int benefitAmount = eventStatus.getBenefitAmount();
        if (benefitAmount == 0) {
            System.out.printf("%d원\n", benefitAmount);
            return;
        }
        System.out.printf("-%,d원\n", benefitAmount);
    }

    public void printTotalPriceWithDiscount(EventStatus eventStatus, AcceptedOrders acceptedOrders) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%,d원\n",
                acceptedOrders.checkTotalPriceWithoutDiscount() - eventStatus.getActualDiscountAmount());
    }

    public void printEventBadge(EventStatus eventStatus) {
        System.out.println("<12월 이벤트 배지>");
        EventBadge eventBadge = EventBadge.findEventBadgeByDiscountPrice(
                TotalPrice.from(eventStatus.getBenefitAmount()));
        System.out.println(eventBadge.getDescription());
    }
}
