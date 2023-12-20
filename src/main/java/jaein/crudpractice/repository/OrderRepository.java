package jaein.crudpractice.repository;

import jaein.crudpractice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o join o.student s where s.name = :name")
    List<Order> findByStudentName(@Param("name") String name);

정    @Query("select o from Order o where o.id = :id")
    Order findOne(@Param("id") Long id);

}
