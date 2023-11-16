package christmas.domain.order;

import static christmas.util.Constants.ERROR_UNVALID_ORDER_MESSAGE;

import christmas.util.Util;
import java.util.Objects;

public class MenuQuantity {
    private static final String UNVALID_PREFIX = "0";
    private final int menuQuantityValue;

    private MenuQuantity(String uncheckedValue) {
        validate(uncheckedValue);
        this.menuQuantityValue = Util.convertStringToInt(uncheckedValue);
    }

    private void validate(String menuQuantity) {
        isStartsWithZero(menuQuantity);
    }

    private void isStartsWithZero(String menuQuantity) {
        if (menuQuantity.startsWith(UNVALID_PREFIX)) {
            throw new IllegalArgumentException(ERROR_UNVALID_ORDER_MESSAGE);
        }
    }

    public static MenuQuantity from(String unconvertedMenuQuantity) {
        return new MenuQuantity(unconvertedMenuQuantity);
    }

    public int getMenuQuantityValue() {
        return menuQuantityValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuQuantity that = (MenuQuantity) o;
        return menuQuantityValue == that.menuQuantityValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuQuantityValue);
    }

    @Override
    public String toString() {
        return "MenuQuantity{" +
                "menuQuantityValue=" + menuQuantityValue +
                '}';
    }
}
