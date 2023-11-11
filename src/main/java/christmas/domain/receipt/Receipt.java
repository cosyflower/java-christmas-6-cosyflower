package christmas.domain.receipt;

public class Receipt {
    // 영수증은 할인 전 전체 가격, 할인 적용 가능한 이벤트 리스트를 유지한다
    private final TotalPrice totalPrice;
    private final AcceptedEvents acceptedEvents;

    private Receipt(TotalPrice totalPrice, AcceptedEvents acceptedEvents) {
        this.totalPrice = totalPrice;
        this.acceptedEvents = acceptedEvents;
    }

    public static Receipt of(TotalPrice totalPrice, AcceptedEvents acceptedEvents) {
        return new Receipt(totalPrice, acceptedEvents);
    }
}
