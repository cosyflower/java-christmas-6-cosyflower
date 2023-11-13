package christmas.dto;

import christmas.util.Constants;

public class DayDTO {
    private final String dateDTO;

    public DayDTO(String dateDTO) {
        validate(dateDTO);
        this.dateDTO = dateDTO;
    }

    private void validate(String dateDTO) {
        isNullOrEmpty(dateDTO);
        isNumber(dateDTO);
    }

    private void isNullOrEmpty(String dateDTO) {
        if (dateDTO == null || dateDTO.isEmpty()) {
            throw new IllegalArgumentException("아무것도 입력하지 않았습니다.");
        }
    }

    private void isNumber(String dateDTO) {
        if (Constants.NUMBER_PATTERN.matcher(dateDTO).matches() == false) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다.");
        }
    }

    public String getDateDTO() {
        return dateDTO;
    }
}
