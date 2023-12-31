package christmas.domain.menu;

import static christmas.util.Constants.ERROR_UNVALID_ORDER_MESSAGE;

import christmas.domain.order.MenuQuantity;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum MenuProduct {
    // 에피타이저
    MUSHROOM_SOUP(MenuType.APPETIZER, MenuName.from("양송이스프"), MenuPrice.from(6000)),
    TAPAS(MenuType.APPETIZER, MenuName.from("타파스"), MenuPrice.from(5500)),
    CAESAR_SALAD(MenuType.APPETIZER, MenuName.from("시저샐러드"), MenuPrice.from(8000)),

    // 메인 음식
    T_BONE_STEAK(MenuType.MAIN_MENU, MenuName.from("티본스테이크"), MenuPrice.from(55000)),
    BARBECUE_RIB(MenuType.MAIN_MENU, MenuName.from("바비큐립"), MenuPrice.from(54000)),
    SEAFOOD_PASTA(MenuType.MAIN_MENU, MenuName.from("해산물파스타"), MenuPrice.from(35000)),
    CHRISTMAS_PASTA(MenuType.MAIN_MENU, MenuName.from("크리스마스파스타"), MenuPrice.from(25000)),

    // 디저트
    CHOCO_CAKE(MenuType.DESERT, MenuName.from("초코케이크"), MenuPrice.from(15000)),
    ICR_CREAM(MenuType.DESERT, MenuName.from("아이스크림"), MenuPrice.from(5000)),

    // 음료
    DIET_COKE(MenuType.DRINK, MenuName.from("제로콜라"), MenuPrice.from(3000)),
    RED_WINE(MenuType.DRINK, MenuName.from("레드와인"), MenuPrice.from(60000)),
    CHAMPAGNE(MenuType.DRINK, MenuName.from("샴페인"), MenuPrice.from(25000))
    ;

    private final MenuType menuType;
    private final MenuName menuName;
    private final MenuPrice price;

    MenuProduct(MenuType menuType, MenuName menuName, MenuPrice price) {
        this.menuType = menuType;
        this.menuName = menuName;
        this.price = price;
    }

    private static Map<MenuName, MenuProduct> menuProductMap = Collections.unmodifiableMap(
            Arrays.stream(values())
                    .collect(Collectors.toMap(((value) -> value.menuName), Function.identity())
                    )
    );

    public static MenuProduct findMenuProductByName(String name) {
        return Optional.ofNullable(menuProductMap.get(MenuName.from(name)))
                .orElseThrow(() -> new IllegalArgumentException(ERROR_UNVALID_ORDER_MESSAGE));
    }

    public boolean isSameMenuType(MenuType otherMenuType) {
        return menuType == otherMenuType;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public MenuName getMenuName() {
        return menuName;
    }

    public int getPriceValue() {
        return price.getPriceValue();
    }

    public int generateTotalPrice(MenuQuantity menuQuantity) {
        return price.multiplyQuantity(menuQuantity);
    }
}
