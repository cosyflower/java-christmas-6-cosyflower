package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.menu.MenuName;
import christmas.menu.MenuProduct;
import christmas.menu.MenuType;
import christmas.menu.MenuPrice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuProductTest {
    @Test
    @DisplayName("MenuProduct 생성 확인")
    void create() {
        MenuProduct mushroomSoup = MenuProduct.MUSHROOM_SOUP;

        assertThat(mushroomSoup.getMenuType()).isEqualTo(MenuType.APPETIZER);
        assertThat(mushroomSoup.getMenuName()).isEqualTo(MenuName.from("양송이 스프"));
        assertThat(mushroomSoup.getPrice()).isEqualTo(MenuPrice.from(6000));
    }
}
