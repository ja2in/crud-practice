package jaein.crudpractice.repository.order.simplequery;

import jaein.crudpractice.domain.Order;
import jaein.crudpractice.domain.OrderStatus;
import lombok.Data;

import java.util.Date;

@Data
public class OrderSimpleQueryDto {
    private Long orderId;
    private String name;
    private Date loanDate;
    private Date returnDate;

    public OrderSimpleQueryDto(Long orderId, String name, Date loanDate, Date returnDate) {
        this.orderId = orderId;
        this.name = name;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }
}

