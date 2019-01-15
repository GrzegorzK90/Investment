package pl.project.investment.investment.service;

import pl.project.investment.investment.enums.TypeImplementation;


public interface CalculationInterface {
    double DAYS_IN_YEAR = 360;
    double DAYS_IN_MONTH = 30;

    //    double calculateInterest(int days, double interest, double amount);
    double calculateInterest(int period, double interest, double amount);

    TypeImplementation getType();

}
