package lv.alija.bookShopMicro2.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizesExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrderClientControllerException.class)
    public final ResponseEntity<Object> handleAllExceptions(OrderClientControllerException ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getMessage(), new Date(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, ex.getErrorCode());
    }
}
