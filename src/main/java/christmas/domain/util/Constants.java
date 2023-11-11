package christmas.domain.util;

import java.util.regex.Pattern;

public class Constants {
    public static final int BADGE_LAST_CRITERA = 20000;
    public static final int BADGE_MIDDEL_CRITERA = 10000;
    public static final int BADGE_FIRST_CRITERA = 5000;

    public static final int INIT_VALUE = 0;

    public static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]+$");
    public static final Pattern ORDER_PATTERN = Pattern.compile("^([ㄱ-ㅎ가-힣]+-[0-9]+)(,[ㄱ-ㅎ가-힣]+-[0-9]+)*$");
}
