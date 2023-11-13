package christmas.controller.display;

import christmas.domain.receipt.EventStatus;
import christmas.view.OutputView;

public class DisplayBenefitController implements DisplayController {
    private final OutputView outputView;
    private final EventStatus eventStatus;

    public DisplayBenefitController(OutputView outputView, EventStatus eventStatus) {
        this.outputView = outputView;
        this.eventStatus = eventStatus;
    }

    @Override
    public void process() {
        outputView.printNewLine();
        outputView.printPromotion(eventStatus);
        outputView.printNewLine();
        outputView.printBenefit(eventStatus);
        outputView.printNewLine();
        outputView.printTotalBenefit(eventStatus);
    }
}
