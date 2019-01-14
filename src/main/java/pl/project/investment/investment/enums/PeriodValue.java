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
        for (PeriodValue p : PeriodValue.values()) {
            if (p.period == i) return p;
        }
        throw new WrongDataException(ErrorMessages.WRONG_VALUE.getErrorMessage());
    }
}
