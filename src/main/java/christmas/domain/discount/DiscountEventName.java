package christmas.domain.discount;

public class DiscountEventName {
    private final String eventNameValue;

    private DiscountEventName(String eventNameVaue) {
        this.eventNameValue = eventNameVaue;
    }

    public static DiscountEventName from(String eventName) {
        return new DiscountEventName(eventName);
    }

    public String getEventNameValue() {
        return eventNameValue;
    }
}
