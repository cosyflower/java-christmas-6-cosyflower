package christmas.util;

import java.util.regex.Pattern;

public class Constants {
    public static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]+$");
    public static final Pattern ORDER_PATTERN = Pattern.compile("^([ㄱ-ㅎ가-힣]+-[0-9]+)(,[ㄱ-ㅎ가-힣]+-[0-9]+)*$");

    public static final int INIT_VALUE = 0;

    public static final int FIRST_DAY = 1;
    public static final int LAST_DAY = 31;
    public static final int CHRISTMAS_DAY = 25;

    public static final int BADGE_LAST_CRITERA = 20000;
    public static final int BADGE_MIDDEL_CRITERA = 10000;
    public static final int BADGE_FIRST_CRITERA = 5000;
    public static final int PROMOTION_CRITERIA = 120000;

    public static final int DISCOUNT_AMOUNT = 2023;
    public static final int INCREASE_AMOUNT = 100;
    public static final int DEFAULT_DISCOUNT_AMOUNT = 1000;
    public static final int PROMOTION_AMOUNT = 25000;

    public static final String ERROR_UNVALID_ORDER_MESSAGE = "유효하지 않은 주문입니다.";
    public static final String ERROR_UNVALID_DAY_MESSAGE = "유효하지 않은 날짜입니다.";
}
