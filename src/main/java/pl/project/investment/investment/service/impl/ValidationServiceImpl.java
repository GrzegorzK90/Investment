package pl.project.investment.investment.service.impl;


import org.springframework.stereotype.Service;
import pl.project.investment.investment.entity.Investment;
import pl.project.investment.investment.enums.ErrorMessages;
import pl.project.investment.investment.exception.WrongDataException;
import pl.project.investment.investment.service.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService {

    private boolean isNegative(int value){
        return value < 0;
    }

    private boolean isNegative(double value){
        return value < 0;
    }

    private boolean isZero(int value){
        return value == 0;
    }

    private boolean isZero(double value){
        return value == 0;
    }

    @Override
    public void isAmountCorrect(double value){
        if(isNegative(value))
            throw new WrongDataException(ErrorMessages.NEGATIVE_VALUE.getErrorMessage());
        else if(isZero(value))
            throw new WrongDataException(ErrorMessages.ZERO_VALUE.getErrorMessage());
    }

    @Override
    public void isInvestmentDateFromToCorrect(Investment investment) {
        if(isNegative(investment.getDateTo().getYear() - investment.getDateFrom().getYear()) ||
                isNegative(investment.getDateTo().getDayOfYear() - investment.getDateFrom().getDayOfYear()))
            throw new WrongDataException(ErrorMessages.NEGATIVE_DAY.getErrorMessage());
        else if (isZero(investment.getDateTo().getYear() - investment.getDateFrom().getYear()) &&
            isZero(investment.getDateTo().getDayOfYear() - investment.getDateFrom().getDayOfYear()))
                throw new WrongDataException(ErrorMessages.ZERO_DAY.getErrorMessage());
    }
}
