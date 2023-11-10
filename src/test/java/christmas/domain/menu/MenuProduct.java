package christmas.domain.menu;

public enum MenuProduct {
    // 에피타이저
    MUSHROOM_SOUP(MenuType.APPETIZER, MenuName.from("양송이 스프"), Price.from(6000)),
    TAPAS(MenuType.APPETIZER, MenuName.from("타파스"), Price.from(5500)),
    CAESAR_SALAD(MenuType.APPETIZER, MenuName.from("시저 샐러드"), Price.from(8000)),

    // 메인 음식
    T_BONE_STEAK(MenuType.MAIN_MENU, MenuName.from("티본 스테이크"), Price.from(55000)),
    BARBECUE_RIB(MenuType.MAIN_MENU, MenuName.from("바베큐 립"), Price.from(54000)),
    SEAFOOD_PASTA(MenuType.MAIN_MENU, MenuName.from("해산물 파스타"), Price.from(35000)),
    CHRISTMAS_PASTA(MenuType.MAIN_MENU, MenuName.from("크리스마스 파스타"), Price.from(25000)),

    // 디저트
    CHOCO_CAKE(MenuType.DESERT, MenuName.from("초코 케이크"), Price.from(15000)),
    ICR_CREAM(MenuType.DESERT, MenuName.from("아이스크림"), Price.from(5000)),

    // 음료
    DIET_COKE(MenuType.DRINK, MenuName.from("제로 콜라"), Price.from(3000)),
    RED_WINE(MenuType.DRINK, MenuName.from("레드 와인"), Price.from(60000)),
    CHAMPAGNE(MenuType.DRINK, MenuName.from("샴페인"), Price.from(25000))
    ;

    private final MenuType menuType;
    private final MenuName menuName;
    private final Price price;

    MenuProduct(MenuType menuType, MenuName menuName, Price price) {
        this.menuType = menuType;
        this.menuName = menuName;
        this.price = price;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public MenuName getMenuName() {
        return menuName;
    }

    public Price getPrice() {
        return price;
    }
}
