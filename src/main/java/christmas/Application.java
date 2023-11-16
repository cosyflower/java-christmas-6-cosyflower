package christmas;

import christmas.system.PlannerApplication;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        PlannerApplication plannerApplication = new PlannerApplication(new InputView(), new OutputView());
        plannerApplication.run();
    }
}
