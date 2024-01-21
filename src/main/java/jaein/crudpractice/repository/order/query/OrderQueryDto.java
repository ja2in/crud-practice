package jaein.crudpractice.repository.order.query;

import jaein.crudpractice.domain.Order;
import jaein.crudpractice.domain.OrderItem;
import jaein.crudpractice.domain.OrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(of = "orderId")
public class OrderQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;
//    private OrderStatus status;
    private List<OrderItemQueryDto> orderItems;

    public OrderQueryDto(Long orderId, String name, LocalDateTime loanDate, LocalDateTime returnDate, List<OrderItemQueryDto> orderItems) {
        this.orderId = orderId;
        this.name = name;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.orderItems = orderItems;
    }

    public OrderQueryDto() {
    }
}
