package christmas.domain.reservation;

public class Day {
    public static final int SPECIAL_DAY = 25;
    public static final int WEEK_TOTAL_DAYS = 7;
    public static final int FIRST_WEEKEND_REMAIN = 1;
    public static final int SECOND_WEEKEND_REMAIN = 2;
    public static final int SPECIAL_REMAIN = 3;
    public static final int ADJUST_DATE = 1;

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
        return isSameRemain(FIRST_WEEKEND_REMAIN) || isSameRemain(SECOND_WEEKEND_REMAIN);
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
        return isSameRemain(SPECIAL_REMAIN);
    }

    private boolean isSameRemain(int remainValue) {
        return dayValue % WEEK_TOTAL_DAYS == remainValue;
    }

    public int calculateDay() {
        return dayValue - ADJUST_DATE;
    }
}
