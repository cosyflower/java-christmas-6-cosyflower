package christmas.menu;

public enum MenuType {
    APPETIZER("appetizer"),
    DESERT("desert"),
    MAIN_MENU("mainMenu"),
    DRINK("mainMenu")
    ;

    private final String description;

    MenuType(String description) {
        this.description = description;
    }


}
