package christmas.dto;

import christmas.Constants;

public class DateDTO {
    private final String dateDTO;

    private DateDTO(String dateDTO) {
        validate(dateDTO);
        this.dateDTO = dateDTO;
    }

    public static DateDTO from(String dateDTO) {
        return new DateDTO(dateDTO);
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
