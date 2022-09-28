package net.uart.uart_marketplace.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "You have broken the DB :(")
public class DBException extends RuntimeException {
    public DBException(Throwable source) {

    }
}
