package net.uart.uart_marketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Item with such ID already exists")
public class ConflictException extends RuntimeException {
}
