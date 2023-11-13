package christmas.domain.discount;

public class DiscountEventName {
    private final String eventNameVaue;

    private DiscountEventName(String eventNameVaue) {
        this.eventNameVaue = eventNameVaue;
    }

    public static DiscountEventName from(String eventName) {
        return new DiscountEventName(eventName);
    }

    public String getEventNameVaue() {
        return eventNameVaue;
    }
}
