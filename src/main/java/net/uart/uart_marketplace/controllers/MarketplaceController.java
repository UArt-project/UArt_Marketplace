package net.uart.uart_marketplace.controllers;

import net.uart.uart_marketplace.dto.MarketItemDTO;
import net.uart.uart_marketplace.exceptions.ConflictException;
import net.uart.uart_marketplace.services.MarketplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/marketplace/v1")
public class MarketplaceController {

    private final MarketplaceService service;

    @Autowired
    public MarketplaceController(MarketplaceService service) {
        this.service = service;
    }

    @GetMapping(value = "/items/{pageNumber}", produces = "application/json")
    public @ResponseBody MarketItemDTO[] getPage(@PathVariable Integer pageNumber,
                                   @RequestParam Optional<Integer> limit) {
        return limit
                .map(integer -> service.getPage(pageNumber, integer)
                        .toArray(new MarketItemDTO[0]))
                .orElseGet(() -> service.getPage(pageNumber)
                        .toArray(new MarketItemDTO[0]));
    }

    @GetMapping("/item/{ID}")
    public @ResponseBody MarketItemDTO getItem(@PathVariable String ID) {
        return service.getItem(ID);
    }

    @PutMapping(value = "/item/{ID}", consumes = "application/json")
    public HttpStatus updateItem(@PathVariable String ID, @RequestBody MarketItemDTO body) {
        service.updateItem(body);
        return HttpStatus.OK;
    }

    @DeleteMapping("/item/{ID}")
    public HttpStatus deleteItem(@PathVariable String ID) {
        service.deleteItem(ID);
        return HttpStatus.OK;
    }

    @PostMapping(value = "/item", consumes = "application/json")
    public HttpStatus createItem(@RequestBody MarketItemDTO body) {
        if (service.checkIfExists(body.getID())) {
            throw new ConflictException();
        }
        service.createItem(body);
        return HttpStatus.CREATED;
    }


}
