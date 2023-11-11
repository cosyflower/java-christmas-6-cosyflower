package christmas;

public class TotalPrice {
    private final int totalPrice;

    private TotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static TotalPrice from(int totalPrice) {
        return new TotalPrice(totalPrice);
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean isLowerThanPromotionCriteria() {
        if (totalPrice < 120000) {
            return true;
        }
        return false;
    }
}
