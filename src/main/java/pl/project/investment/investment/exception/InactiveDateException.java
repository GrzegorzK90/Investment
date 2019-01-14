package pl.project.investment.investment.exception;

class InactiveDateException extends RuntimeException {

    private static final long serialVersionUID = 324;

    public InactiveDateException(String message) {
        super(message);
    }
}
