package christmas.domain.order;

import christmas.domain.menu.MenuProduct;
import christmas.domain.menu.MenuType;
import java.util.Objects;

public class Order {
    private static final String ORDER_SEPARATOR = "-";
    private static final int MENU_PRODUCT_INDEX = 0;
    private static final int MENU_QUANTITY_INDEX = 1;

    private final MenuProduct menuProduct; // 메뉴
    private final MenuQuantity menuQuantity; // 수량

    private Order(String menuAndNumber) { // "양송이스프-2" 가 들어온 상황
        String[] split = menuAndNumber.split(ORDER_SEPARATOR);
        this.menuProduct = MenuProduct.findMenuProductByName(split[MENU_PRODUCT_INDEX]);
        this.menuQuantity = MenuQuantity.from(split[MENU_QUANTITY_INDEX]);
    }

    public static Order from(String menuAndNumber) {
        return new Order(menuAndNumber);
    }

    public int getEachOrderTotalPrice() {
        return menuProduct.generateTotalPrice(menuQuantity);
    }

    public boolean hasSameMenuType(MenuType menuType) {
        return menuProduct.isSameMenuType(menuType);
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

    public MenuProduct getMenuProduct() {
        return menuProduct;
    }

    public MenuQuantity getMenuQuantity() {
        return menuQuantity;
    }

    public String getMenuNameValue() {
        return menuProduct.getMenuName().getMenuNameValue();
    }

    public int getMenuQuantityValue() {
        return menuQuantity.getMenuQuantityValue();
    }
}
