package pl.project.investment.investment.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.project.investment.investment.enums.ErrorMessages;
import pl.project.investment.investment.response.ErrorMessage;

import java.util.Date;

@ControllerAdvice
public class AppExceptionsHandler {

    @ExceptionHandler(value = {NotFoundException.class, WrongDataException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), ex.toString());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InactiveDateException.class})
    public ResponseEntity<Object> handleAfterDateException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), ex.toString());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ErrorMessages.CONVERSION_TYPE_ERROR.getErrorMessage(),
                ex.getLocalizedMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleAssertionError(MethodArgumentNotValidException ex) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), ex.toString());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
