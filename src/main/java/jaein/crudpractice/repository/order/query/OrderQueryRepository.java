package jaein.crudpractice.repository.order.query;

import jaein.crudpractice.domain.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderQueryRepository extends JpaRepository<Order, Long> {

    @Query("select distinct new jaein.crudpractice.repository.order.query.OrderQueryDto(o.id, s.name, o.loanDate, o.returnDate, " +
            "oi.item.name, oi.count) " +
            "from Order o " +
            "join o.student s " +
            "join o.loan l " +
            "join o.orderItems oi")
    List<OrderQueryDto> findOrderQueryDtos(Pageable pageable);


    @Query("select distinct new jaein.crudpractice.repository.order.query.OrderItemQueryDto(" +
            "oi.order.id, i.name, oi.count)" +
            " from OrderItem oi " +
            " join oi.item i " +
            " where oi.order.id = :orderId")
    List<OrderItemQueryDto> findOrderItems(@Param("orderId") Long orderId);

}
