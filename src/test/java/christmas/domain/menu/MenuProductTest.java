package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import christmas.domain.order.MenuQuantity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuProductTest {
    @Test
    @DisplayName("MenuProduct 생성 확인")
    void create() {
        MenuProduct mushroomSoup = MenuProduct.MUSHROOM_SOUP;

        assertThat(mushroomSoup.getMenuType()).isEqualTo(MenuType.APPETIZER);
        assertThat(mushroomSoup.getMenuName()).isEqualTo(MenuName.from("양송이스프"));
        assertThat(mushroomSoup.getPriceValue()).isEqualTo(6000);
    }

    @ParameterizedTest(name = "존재하지 않는 메뉴 이름 - {0}")
    @ValueSource(strings = {"와이스테이크", "코코아", "콘치즈"})
    void 존재하지_않는_메뉴면_예외(String unvalidName) {
        assertThatCode(() -> MenuProduct.findMenuProductByName(unvalidName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 주문입니다.");
    }

    @ParameterizedTest(name = "존재하는 메뉴 이름 - {0}")
    @ValueSource(strings = {"초코케이크", "양송이스프", "티본스테이크"})
    void 존재하는_메뉴면_성공(String validName) {
        assertThatCode(() -> MenuProduct.findMenuProductByName(validName))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "수량 : {0} 결과 : {1}")
    @CsvSource(value = {"1:25000", "2:50000", "3:75000", "4:100000"}, delimiter = ':')
    void 해당_메뉴_가격과_수량을_활용한_금액_구하기(String quantity, int total){
        int totalPrice = MenuProduct.CHAMPAGNE.generateTotalPrice(MenuQuantity.from(quantity));

        assertThat(totalPrice).isEqualTo(total);
    }
}
