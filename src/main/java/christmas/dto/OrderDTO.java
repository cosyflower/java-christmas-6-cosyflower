package christmas.dto;

import static christmas.util.Constants.ERROR_UNVALID_ORDER_MESSAGE;

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
            throw new IllegalArgumentException(ERROR_UNVALID_ORDER_MESSAGE);
        }
    }

    private void isOrderPattern(String order) {
        if (Constants.ORDER_PATTERN.matcher(order).matches() == false) {
            throw new IllegalArgumentException(ERROR_UNVALID_ORDER_MESSAGE);
        }
    }

    public String getMenuAndNumber() {
        return menuAndNumber;
    }
}
