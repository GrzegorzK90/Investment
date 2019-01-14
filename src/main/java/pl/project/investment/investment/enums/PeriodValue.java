package pl.project.investment.investment.enums;

import pl.project.investment.investment.exception.WrongDataException;

public enum PeriodValue {
    ONE_MONTHS(1),
    THREE_MONTHS(3),
    SIX_MONTHS(6),
    TWELVE_MONTHS(12);

    private final int period;

    PeriodValue(int period) {
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public static PeriodValue valueOf(int i) {
        switch (i) {
            case 1:
                return ONE_MONTHS;
            case 3:
                return THREE_MONTHS;
            case 6:
                return SIX_MONTHS;
            case 12:
                return TWELVE_MONTHS;
        }
        throw new WrongDataException(ErrorMessages.WRONG_VALUE.getErrorMessage());
    }
}
