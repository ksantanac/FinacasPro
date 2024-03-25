package br.com.fiap.validation;

import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.fiap.validation.ValidationErrorHandler.ValidationError;

@RestControllerAdvice
public class ValidationErrorHandler {

    record ValidationError(String campo, String mensamgem){
        public ValidationError(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage())
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public List<ValidationError> handle(MethodArgumentNotValidException exception){

        return exception
                    .getFieldError()
                    .stream()
                    .map(ValidationError::new) // convertendo o fielderror pra validation error
                    .toList();
    }
}
