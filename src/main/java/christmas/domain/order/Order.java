package christmas.domain.order;

import static christmas.domain.menu.MenuProduct.findMenuProductByName;

import christmas.domain.menu.MenuProduct;
import java.util.Objects;

public class Order {
    private final MenuProduct menuProduct; // 메뉴 타입
    private final MenuQuantity menuQuantity; // 수량

    private Order(String menuAndNumber) {// "양송이스프-2" 가 들어온 상황
        String[] split = menuAndNumber.split("-");
        this.menuProduct = findMenuProductByName(split[0]);
        this.menuQuantity = MenuQuantity.from(split[1]);
    }

    public static Order from(String menuAndNumber) {
        return new Order(menuAndNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return menuProduct == order.menuProduct && Objects.equals(menuQuantity, order.menuQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuProduct, menuQuantity);
    }
}
