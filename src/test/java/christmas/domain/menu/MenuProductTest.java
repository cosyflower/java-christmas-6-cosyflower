package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.MenuName;
import christmas.domain.menu.MenuProduct;
import christmas.domain.menu.MenuType;
import christmas.domain.menu.MenuPrice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuProductTest {
    @Test
    @DisplayName("MenuProduct 생성 확인")
    void create() {
        MenuProduct mushroomSoup = MenuProduct.MUSHROOM_SOUP;

        assertThat(mushroomSoup.getMenuType()).isEqualTo(MenuType.APPETIZER);
        assertThat(mushroomSoup.getMenuName()).isEqualTo(MenuName.from("양송이스프"));
        assertThat(mushroomSoup.getPrice()).isEqualTo(MenuPrice.from(6000));
    }
}
