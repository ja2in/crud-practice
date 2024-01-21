package jaein.crudpractice.repository.order.query;

import jaein.crudpractice.domain.OrderItem;
import lombok.Data;

@Data
public class OrderItemQueryDto {

    private Long orderId;
    private String itemName;
    private int count;

    public OrderItemQueryDto(Long orderId, String itemName, int count) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.count = count;
    }

    public OrderItemQueryDto() {
    }
}
