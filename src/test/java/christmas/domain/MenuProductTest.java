package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.MenuName;
import christmas.domain.menu.MenuProduct;
import christmas.domain.menu.MenuType;
import christmas.domain.menu.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuProductTest {
    @Test
    @DisplayName("MenuProduct 생성 확인")
    void create() {
        MenuProduct mushroomSoup = MenuProduct.MUSHROOM_SOUP;

        assertThat(mushroomSoup.getMenuType()).isEqualTo(MenuType.APPETIZER);
        assertThat(mushroomSoup.getMenuName()).isEqualTo(MenuName.from("양송이 스프"));
        assertThat(mushroomSoup.getPrice()).isEqualTo(Price.from(6000));
    }
}
