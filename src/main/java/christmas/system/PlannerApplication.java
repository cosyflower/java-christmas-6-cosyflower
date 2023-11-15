package christmas.system;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.display.DisplayAcceptedOrdersController;
import christmas.controller.display.DisplayBadgeController;
import christmas.controller.display.DisplayBenefitController;
import christmas.controller.register.RegisterDayController;
import christmas.controller.register.RegisterOrderController;
import christmas.domain.discount.DiscountChecker;
import christmas.domain.order.AcceptedOrders;
import christmas.domain.receipt.EventStatus;
import christmas.domain.reservation.Day;
import christmas.domain.reservation.EventReservation;
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
        // Day, AcceptedOrders 등록
        Day day = new RegisterDayController(inputView, outputView).process();
        AcceptedOrders acceptedOrders = new RegisterOrderController(inputView).process();
        // AcceptedOrders 출력
        new DisplayAcceptedOrdersController(outputView, acceptedOrders).process();
        // EventReservation 등록, EventStatus 생성
        EventReservation eventReservation = EventReservation.of(day, acceptedOrders);
        EventStatus eventStatus = DiscountChecker.findAllDiscountPrice(eventReservation);
        // 결과 출력
        new DisplayBenefitController(outputView, eventStatus).process();
        new DisplayBadgeController(outputView, eventStatus, acceptedOrders).process();
        Console.close();
    }
}
