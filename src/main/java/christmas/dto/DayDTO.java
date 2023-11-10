package christmas.dto;

import christmas.Constants;

public class DayDTO {
    private final String dateDTO;

    private DayDTO(String dateDTO) {
        validate(dateDTO);
        this.dateDTO = dateDTO;
    }

    public static DayDTO from(String dateDTO) {
        return new DayDTO(dateDTO);
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
            throw new IllegalArgumentException("수를 입력하지 않았습니다.");
        }
    }
}
