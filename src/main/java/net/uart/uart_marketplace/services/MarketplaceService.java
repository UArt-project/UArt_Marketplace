package net.uart.uart_marketplace.services;

import lombok.val;
import net.uart.uart_marketplace.dto.MarketItemDTO;
import net.uart.uart_marketplace.entities.MarketItem;
import net.uart.uart_marketplace.exceptions.DBException;
import net.uart.uart_marketplace.exceptions.ItemNotFoundException;
import net.uart.uart_marketplace.exceptions.TooManyItemsOnPageException;
import net.uart.uart_marketplace.repositpories.MarketItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarketplaceService {

    private final MarketItemRepository repository;

    @Autowired
    public MarketplaceService(MarketItemRepository repository) {
        this.repository = repository;
    }

    public List<MarketItemDTO> getPage(int pageNumber, int limit) {
        Pageable pageRequest = PageRequest.of(pageNumber, limit);
        if (limit > 100) {
            throw new TooManyItemsOnPageException("You tried to ask for %s items on page, maximum is 100");
        }
        return repository.findAll(pageRequest).getContent()
                .stream()
                .map(MarketItemDTO::mapFromEntity)
                .collect(Collectors.toList());
    }

    public List<MarketItemDTO> getPage(int pageNumber) {
        return getPage(pageNumber, 10);
    }

    public MarketItemDTO getItem(String id) {
        try {
            val item = repository.findByID(id);
            return item.map(MarketItemDTO::mapFromEntity)
                    .orElseThrow(ItemNotFoundException::new);
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public boolean checkIfExists(String ID) {
        return repository.existsByID(ID);
    }

    public void createItem(MarketItemDTO item) {
        try {
            repository.save(new MarketItem(item));
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public void updateItem(MarketItemDTO item) {
        try {
            repository.updateByID(item.getID(), item.getName(), item.getPhotoLink(), item.getPrice());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public void deleteItem(String id) {
        try {
            repository.deleteByID(id);
        } catch (Exception e) {
            throw new DBException(e);
        }
    }
}
