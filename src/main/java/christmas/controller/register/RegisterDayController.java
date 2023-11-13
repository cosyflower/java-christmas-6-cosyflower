package christmas.controller.register;

import christmas.domain.reservation.Day;
import christmas.dto.DayDTO;
import christmas.util.Mapper;
import christmas.view.InputView;
import christmas.view.OutputView;

public class RegisterDayController extends RegisterAbstractController<Day> {
    // 방문 날짜를 입력받는다
    private final InputView inputView;
    private final OutputView outputView;

    public RegisterDayController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    @Override
    Day doProcess() {
        //데이터를 교환한다
        outputView.printStartMessage();
        DayDTO dayDTO = new DayDTO(inputView.readDate());
        return Mapper.toDay(dayDTO);
    }

}
