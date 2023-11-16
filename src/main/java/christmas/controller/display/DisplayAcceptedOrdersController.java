package christmas.controller.display;

import christmas.domain.order.AcceptedOrders;
import christmas.view.OutputView;

public class DisplayAcceptedOrdersController implements DisplayController {
    private final OutputView outputView;
    private final AcceptedOrders acceptedOrders;

    public DisplayAcceptedOrdersController(OutputView outputView, AcceptedOrders acceptedOrders) {
        this.outputView = outputView;
        this.acceptedOrders = acceptedOrders;
    }

    @Override
    public void process() {
        outputView.printEventDescription();
        outputView.printNewLine();
        outputView.printAcceptedOrders(acceptedOrders);

        outputView.printNewLine();
        outputView.printTotalPrcieWithoutDiscount(acceptedOrders.checkTotalPriceWithoutDiscount());
    }
}
