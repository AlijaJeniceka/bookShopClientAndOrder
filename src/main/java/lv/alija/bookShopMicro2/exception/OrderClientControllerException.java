package lv.alija.bookShopMicro2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class OrderClientControllerException extends RuntimeException{
    private HttpStatus errorCode;
    private String message;
}
