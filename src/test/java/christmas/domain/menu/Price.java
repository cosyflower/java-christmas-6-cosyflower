package christmas.domain.menu;

import java.util.Objects;

public class Price {
    private final int price;

    private Price(int price) {
        this.price = price;
    }

    public static Price from(int price) {
        return new Price(price);
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
        Price price1 = (Price) o;
        return getPrice() == price1.getPrice();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice());
    }
}
