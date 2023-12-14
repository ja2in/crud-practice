package jaein.crudpractice.repository;

import jaein.crudpractice.domain.Order;
import jaein.crudpractice.domain.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.aspectj.weaver.ast.Or;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderRepositoryTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void orderTest() throws Exception{
        //given
        Order order = new Order(new Student(123, "kim", "A"));
        orderRepository.save(order);

        //when
        List<Order> findOrder = orderRepository.findByStudentName("kim");

        //then
        Assertions.assertThat(findOrder).containsExactly(order);

    }
}