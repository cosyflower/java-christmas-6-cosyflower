package christmas.controller.display;

import christmas.domain.order.AcceptedOrders;
import christmas.domain.receipt.EventStatus;
import christmas.view.OutputView;

public class DisplayBadgeController implements DisplayController {
    private final OutputView outputView;
    private final EventStatus eventStatus;
    private final AcceptedOrders acceptedOrders;

    public DisplayBadgeController(OutputView outputView, EventStatus eventStatus,
                                  AcceptedOrders acceptedOrders) {
        this.outputView = outputView;
        this.eventStatus = eventStatus;
        this.acceptedOrders = acceptedOrders;
    }

    @Override
    public void process() {
        outputView.printNewLine();
        outputView.printTotalPriceWithDiscount(eventStatus, acceptedOrders);
        outputView.printNewLine();
        outputView.printEventBadge(eventStatus);
    }
}
