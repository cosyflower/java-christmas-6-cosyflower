package christmas.system;

import christmas.controller.display.DisplayAcceptedOrdersController;
import christmas.controller.display.DisplayBenefitController;
import christmas.controller.register.RegisterDayController;
import christmas.controller.register.RegisterOrderController;
import christmas.domain.discount.DiscountChecker;
import christmas.domain.discount.DiscountPrice;
import christmas.domain.discount.DiscountType;
import christmas.domain.order.AcceptedOrders;
import christmas.domain.receipt.EventStatus;
import christmas.domain.reservation.Day;
import christmas.domain.reservation.EventReservation;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class PlannerApplication {
    private final InputView inputView;
    private final OutputView outputView;

    public PlannerApplication(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        // 전체 로직의 흐름
        Day day = new RegisterDayController(inputView, outputView).process();
        AcceptedOrders acceptedOrders = new RegisterOrderController(inputView).process();

        new DisplayAcceptedOrdersController(outputView, acceptedOrders).process();

        EventReservation eventReservation = EventReservation.of(day, acceptedOrders);
        EventStatus eventStatus = DiscountChecker.findAllDiscountPrice(eventReservation);

        new DisplayBenefitController(outputView, eventStatus).process();
    }
}
