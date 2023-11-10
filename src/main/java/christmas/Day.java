package christmas;

public class Day {
    public static final int SPECIAL_DAY = 25;
    private final int dayValue;

    private Day(int dayValue) {
        this.dayValue = dayValue;
    }

    public static Day from(int dayValue) {
        return new Day(dayValue);
    }

    public boolean isWeekend() {
        return isSameRemain(1) || isSameRemain(2);
    }

    public boolean isWeekDay() {
        return !isWeekend();
    }

    private boolean isSameRemain(int remainValue) {
        return dayValue % 7 == remainValue;
    }

    public boolean isBeforeThan(int day) {
        return dayValue <= day;
    }

    public boolean isSpecialDay() {
        if (dayValue == SPECIAL_DAY) {
            return true;
        }
        return isSameRemain(3);
    }

    public int getDay() {
        return dayValue;
    }
}
