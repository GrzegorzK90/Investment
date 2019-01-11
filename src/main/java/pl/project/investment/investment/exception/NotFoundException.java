package pl.project.investment.investment.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5978986178887633859L;

    public NotFoundException(String message) {
        super(message);
    }
}
