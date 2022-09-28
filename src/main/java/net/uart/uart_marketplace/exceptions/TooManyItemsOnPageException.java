package net.uart.uart_marketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Too many items requested")
public class TooManyItemsOnPageException extends RuntimeException{
    public TooManyItemsOnPageException(String message) {

    }
}
