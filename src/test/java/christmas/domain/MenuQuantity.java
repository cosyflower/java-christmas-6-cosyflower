package christmas.domain;

import java.util.Objects;

public class MenuQuantity {
    private final int menuQuantity;

    private MenuQuantity(String uncheckedValue) {
        validate(uncheckedValue);
        this.menuQuantity = Integer.parseInt(uncheckedValue);
    }

    private void validate(String menuQuantity) {
        // 숫자 입력은 미리 DTO에서 검증된 상황
        // 0으로 시작하는 예외
        isStartsWithZero(menuQuantity);
    }

    private void isStartsWithZero(String menuQuantity) {
        if (menuQuantity.startsWith("0")) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다");
        }
    }

    public static MenuQuantity from(String unconvertedMenuQuantity) {
        return new MenuQuantity(unconvertedMenuQuantity);
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
        return menuQuantity == that.menuQuantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuQuantity);
    }
}