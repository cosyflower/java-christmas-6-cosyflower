package christmas.menu;

import java.util.Objects;

public class MenuPrice {
    private final int price;

    private MenuPrice(int price) {
        this.price = price;
    }

    public static MenuPrice from(int price) {
        return new MenuPrice(price);
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuPrice price1 = (MenuPrice) o;
        return getPrice() == price1.getPrice();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice());
    }
}
