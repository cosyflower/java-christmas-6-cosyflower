package christmas.dto;

import static christmas.util.Constants.ERROR_UNVALID_DAY_MESSAGE;

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
            throw new IllegalArgumentException(ERROR_UNVALID_DAY_MESSAGE);
        }
    }

    private void isNumber(String dateDTO) {
        if (Constants.NUMBER_PATTERN.matcher(dateDTO).matches() == false) {
            throw new IllegalArgumentException(ERROR_UNVALID_DAY_MESSAGE);
        }
    }

    public String getDateDTO() {
        return dateDTO;
    }
}
