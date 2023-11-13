package christmas.view;

import christmas.domain.order.AcceptedOrders;
import christmas.domain.order.Order;
import java.util.List;

public class OutputView {
    public void printMenu() {
        System.out.println("<주문 메뉴>");
        // ...
    }

    public void printMessage(String message) {
        System.out.println(message);
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
        System.out.printf("%,d\n", checkTotalPriceWithoutDiscount);
    }
}
