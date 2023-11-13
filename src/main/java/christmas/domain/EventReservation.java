package christmas.domain;

import christmas.domain.menu.MenuType;
import christmas.domain.order.AcceptedOrders;

public class EventReservation {
    private final Day day;
    private final AcceptedOrders acceptedOrders;

    private EventReservation(Day day, AcceptedOrders acceptedOrders) {
        this.day = day;
        this.acceptedOrders = acceptedOrders;
    }

    public static EventReservation of(Day day, AcceptedOrders acceptedOrders) {
        return new EventReservation(day, acceptedOrders);
    }

    public int getAccumulatedDays() {
        return day.calculateDay(); // dayValue - 1
    }

    public int getSpecificMenuTypeTotal(MenuType menuType) {
        return acceptedOrders.getSpecificMenuQuantity(menuType);
    }

    public boolean checkPromotion(int promotionCriteria) {
        return acceptedOrders.canGetPromotion(promotionCriteria);
    }

    public boolean isWeekDay() {
        return day.isWeekDay();
    }

    public boolean isWeekend() {
        return day.isWeekend();
    }

    public boolean isBeforeThan(int dayValue) {
        return day.isBeforeThan(dayValue);
    }

    public boolean isSpecialDay() {
        return day.isSpecialDay();
    }
}
