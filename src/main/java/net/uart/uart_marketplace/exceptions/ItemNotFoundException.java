package net.uart.uart_marketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Item with such ID is missing in the DB")
public class ItemNotFoundException extends RuntimeException {
}
