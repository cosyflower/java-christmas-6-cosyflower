package christmas.domain.menu;

import christmas.domain.order.MenuQuantity;

public class MenuPrice {
    private final int priceValue;

    private MenuPrice(int price) {
        this.priceValue = price;
    }

    public static MenuPrice from(int price) {
        return new MenuPrice(price);
    }

    public int multiplyQuantity(MenuQuantity menuQuantity) {
        return priceValue * menuQuantity.getMenuQuantityValue();
    }

    public int getPriceValue() {
        return priceValue;
    }
}
