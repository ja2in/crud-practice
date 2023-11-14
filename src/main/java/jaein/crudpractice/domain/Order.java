package jaein.crudpractice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private Student student;

    private List<OrderItem> orderItems = new ArrayList<>();

    private Loan loan; //대출

    private Date loanDate; //대출일

    private Date returnDate; //반납일

    public Order() {
    }
}
