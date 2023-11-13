package christmas.controller.register;

import christmas.domain.order.AcceptedOrders;
import christmas.dto.OrderDTO;
import christmas.util.Mapper;
import christmas.view.InputView;

public class RegisterOrderController extends RegisterAbstractController<AcceptedOrders> {
    // 주문을 입력받는다
    private final InputView inputView;;

    public RegisterOrderController(InputView inputView) {
        this.inputView = inputView;
    }
    @Override
    AcceptedOrders doProcess() {
        OrderDTO orderDTO = new OrderDTO(inputView.readMenuAndQuantity());
        return Mapper.toAcceptedOrders(orderDTO);
    }

}
