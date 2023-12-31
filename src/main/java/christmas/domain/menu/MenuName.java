package christmas.domain.menu;

import java.util.Objects;

public class MenuName {
    private final String menuName;

    private MenuName(String menuName) {
        this.menuName = menuName;
    }

    public static MenuName from(String menuName) {
        return new MenuName(menuName);
    }

    public String getMenuNameValue() {
        return menuName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuName menuName1 = (MenuName) o;
        return Objects.equals(menuName, menuName1.menuName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuName);
    }
}
