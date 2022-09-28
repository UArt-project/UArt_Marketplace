package net.uart.uart_marketplace.dto;

import lombok.Builder;
import lombok.Data;
import net.uart.uart_marketplace.entities.MarketItem;

@Data
@Builder
public class MarketItemDTO {

    private String ID;

    private String name;

    private String photoLink;

    private Double price;

    public static MarketItemDTO mapFromEntity(MarketItem item) {
        return MarketItemDTO.builder()
                .ID(item.getID())
                .name(item.getName())
                .price(item.getPrice())
                .photoLink(item.getPhotoLink())
                .build();
    }
}
