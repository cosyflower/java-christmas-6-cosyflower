package christmas.controller.register;

import christmas.view.OutputView;

public abstract class RegisterAbstractController<E> implements RegisterController<E> {
    private final OutputView outputView = new OutputView();

    @Override
    public E process() {
        try {
            return doProcess();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return process();
        }
    }

    abstract E doProcess();
}
