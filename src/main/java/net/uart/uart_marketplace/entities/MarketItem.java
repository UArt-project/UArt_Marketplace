package net.uart.uart_marketplace.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.uart.uart_marketplace.dto.MarketItemDTO;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "MarketItem")
@Table(name = "marketitems")
@Scope(value = "prototype")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class MarketItem {

    @SequenceGenerator(
            name = "marketitem_sequence",
            sequenceName = "marketitem_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = IDENTITY,
            generator = "marketitem_sequence"
    )
    @Column(
            columnDefinition = "serial",
            name = "db_id",
            updatable = false,
            nullable = false
    )
    @Id
    private Long dbId;

    @Column(name = "id", columnDefinition = "TEXT", unique = true)
    private String ID;

    @Column(name = "name", columnDefinition = "TEXT")
    private String name;

    @Column(name = "photo_link", columnDefinition = "TEXT")
    private String photoLink;

    @Column(name = "price")
    private Double price;

    public MarketItem(MarketItemDTO dto) {
        this.ID = dto.getID();
        this.name = dto.getName();
        this.photoLink = dto.getPhotoLink();
        this.price = dto.getPrice();
    }
}
