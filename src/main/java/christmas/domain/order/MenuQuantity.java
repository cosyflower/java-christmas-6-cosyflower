package christmas.domain.order;

import christmas.util.Util;
import java.util.Objects;

public class MenuQuantity {
    private final int menuQuantityValue;

    private MenuQuantity(String uncheckedValue) {
        validate(uncheckedValue);
        this.menuQuantityValue = Util.convertStringToInt(uncheckedValue);
    }

    private void validate(String menuQuantity) {
        // 숫자 입력은 미리 DTO에서 검증된 상황
        // 0으로 시작하는 예외
        isStartsWithZero(menuQuantity);
    }

    private void isStartsWithZero(String menuQuantity) {
        if (menuQuantity.startsWith("0")) {
            throw new IllegalArgumentException("(0으로 시작한 수) 유효하지 않은 주문입니다");
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
}
