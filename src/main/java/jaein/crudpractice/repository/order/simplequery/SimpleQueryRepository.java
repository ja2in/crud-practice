package jaein.crudpractice.repository.order.simplequery;

import jaein.crudpractice.domain.Loan;
import jaein.crudpractice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SimpleQueryRepository extends JpaRepository<Order, Loan> {
    @Query("select new jaein.crudpractice.repository.order.simplequery.OrderSimpleQueryDto(" +
            "o.id, s.name, o.loanDate, o.returnDate) " +
            "from Order o " +
            "join o.student s")
    List<OrderSimpleQueryDto> findOrderDtos();
}
