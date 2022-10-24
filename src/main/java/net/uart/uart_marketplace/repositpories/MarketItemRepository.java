package net.uart.uart_marketplace.repositpories;

import net.uart.uart_marketplace.entities.MarketItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface MarketItemRepository extends PagingAndSortingRepository<MarketItem, Long> {

    Optional<MarketItem> findByID(String ID);

    boolean existsByID(String ID);

    @Transactional
    void deleteByID(String ID);

    @Modifying
    @Transactional
    @Query("UPDATE MarketItem i SET i.name = ?2, i.photoLink = ?3, i.price = ?4 WHERE i.ID = ?1")
    void updateByID(String ID, String name, String photoLink, Double price);
}
