package pl.project.investment.investment.exception;

public class WrongDataException extends RuntimeException {

	private static final long serialVersionUID = 5970123902750362091L;

	public WrongDataException(String message) {
		super(message);
	}
}
