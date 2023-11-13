package christmas.system;

import christmas.controller.register.RegisterDayController;
import christmas.controller.register.RegisterOrderController;
import christmas.domain.order.AcceptedOrders;
import christmas.domain.reservation.Day;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PlannerApplication {
    private final InputView inputView;
    private final OutputView outputView;

    public PlannerApplication(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        // 전체 로직의 흐름
        Day process = new RegisterDayController(inputView, outputView).process();
        AcceptedOrders acceptedOrders = new RegisterOrderController(inputView).process();
    }
}
