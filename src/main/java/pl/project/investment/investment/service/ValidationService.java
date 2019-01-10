package pl.project.investment.investment.service;

import pl.project.investment.investment.entity.Investment;

public interface ValidationService {
    void isAmountCorrect(double value);
    void isInvestmentDateFromToCorrect(Investment investment);
}
