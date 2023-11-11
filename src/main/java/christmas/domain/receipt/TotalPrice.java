package christmas.domain.receipt;

public class TotalPrice {
    public static final int LIMIT = 10000;
    public static final int PROMOTION_CRITERIA = 120000;
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
        if (totalPrice < PROMOTION_CRITERIA) {
            return true;
        }
        return false;
    }

    public boolean isLowerThanLimit() {
        if (totalPrice < LIMIT) {
            return true;
        }
        return false;
    }
}
