package pl.project.investment.investment.service;

public interface ValidationService {
    boolean isNegative(int value);
    boolean isNegative(double value);
    boolean isZero(int value);
    boolean isZero(double value);
}
