package pl.project.investment.investment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Zero Exception")
public class WrongDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WrongDataException(String message) {
		super(message);
	}
}
