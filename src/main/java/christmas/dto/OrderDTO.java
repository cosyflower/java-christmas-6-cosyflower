package christmas.dto;

import christmas.util.Constants;

public class OrderDTO {
    private final String menuAndNumber;

    public OrderDTO(String menuAndNumber) {
        validate(menuAndNumber);
        this.menuAndNumber = menuAndNumber;
    }

    private void validate(String menuAndNumber) {
        isNullOrEmpty(menuAndNumber);
        isOrderPattern(menuAndNumber);
    }

    private void isNullOrEmpty(String order) {
        if (order == null || order.isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다.");
        }
    }

    private void isOrderPattern(String order) {
        if (Constants.ORDER_PATTERN.matcher(order).matches() == false) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다.");
        }
    }

    public String getMenuAndNumber() {
        return menuAndNumber;
    }
}
