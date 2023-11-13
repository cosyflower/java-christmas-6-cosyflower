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
            return process(); // 재귀를 적용하면 문제가 될 수 있다고 했다. 시간 제한 조건을 넣어볼까??
        }
    }

    abstract E doProcess();
}
