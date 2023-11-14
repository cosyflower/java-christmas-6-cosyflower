package christmas.domain.reservation;

import static christmas.util.Constants.ERROR_UNVALID_DAY_MESSAGE;
import static christmas.util.Constants.FIRST_DAY;
import static christmas.util.Constants.LAST_DAY;

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
        if (dayValue > LAST_DAY || dayValue < FIRST_DAY) {
            throw new IllegalArgumentException(ERROR_UNVALID_DAY_MESSAGE);
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
