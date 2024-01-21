package jaein.crudpractice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_id")
    private Loan loan; //대출

    private LocalDateTime loanDate; //대출일

    private LocalDateTime returnDate; //반납일

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문(예약)가능 상태 CAN, CANT

    public Order(Student student) {
        this.student = student;
    }


//    public static Order createOrder(student, Loan loan, OrderItem... orderItems) {
//        Order order = new Order();
//
//        order.setStudent(student);
//        order.setLoan(loan);
//        for (OrderItem orderItem : orderItems) {
//            order.addOrderItem(orderItem);
//        }
//
//        order.setStatus(OrderStatus.CAN);
//        order.setLoanDate(new Date());
//        return order;
//
//    }

    public static Order createOrder(Student student, Loan loan, OrderItem... orderItems){
        Order order = new Order();
        order.setStudent(student);
        order.setLoan(loan);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        order.setStatus(OrderStatus.CAN);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime back = now.plus(14, ChronoUnit.DAYS);

        order.setLoanDate(now);
        order.setReturnDate(back);
        return order;
    }

    private void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void cancel(){
        if(loan.getState() == LoanState.X){
            throw new IllegalStateException("이미 대여된 도서는 대여 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANT);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }
}
