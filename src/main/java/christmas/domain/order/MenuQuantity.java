package christmas.domain.order;

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
            throw new IllegalArgumentException("유효하지 않은 주문입니다");
        }
    }

    public static MenuQuantity from(String unconvertedMenuQuantity) {
        return new MenuQuantity(unconvertedMenuQuantity);
    }

    public int getMenuQuantityValue() {
        return menuQuantityValue;
    }

    @Override
    public boolean equals(Object o) { // 활용되는지 확인
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuQuantity that = (MenuQuantity) o;
        return menuQuantityValue == that.menuQuantityValue;
    }

    @Override // 활용되는지 확인
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
