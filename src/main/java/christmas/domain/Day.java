package christmas.domain;

public class Day {
    public static final int SPECIAL_DAY = 25;
    private final int dayValue;

    private Day(int dayValue) {
        validate(dayValue);
        this.dayValue = dayValue;
    }

    private void validate(int dayValue) {
        isInRange(dayValue);
    }

    private void isInRange(int dayValue) {
        if (dayValue > 31 || dayValue < 1) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다.");
        }
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

    public boolean isBeforeThan(int day) {
        return dayValue <= day;
    }

    public boolean isSpecialDay() {
        if (dayValue == SPECIAL_DAY) {
            return true;
        }
        return isSameRemain(3);
    }

    private boolean isSameRemain(int remainValue) {
        return dayValue % 7 == remainValue;
    }



    public int getDay() {
        return dayValue;
    }
}
