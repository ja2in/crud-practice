package jaein.crudpractice;

import jaein.crudpractice.domain.Loan;
import jaein.crudpractice.domain.Order;
import jaein.crudpractice.domain.OrderItem;
import jaein.crudpractice.domain.Student;
import jaein.crudpractice.domain.item.Book;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Student student = new Student();
            student.setName("kim");
            em.persist(student);

            Book book1 = new Book();
            book1.setName("JAVA");
            book1.setStockQuantity(20);
            em.persist(book1);

            Book book2 = new Book();
            book2.setName("Spring");
            book2.setStockQuantity(30);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 3);

            Loan loan = new Loan();
            Order order = Order.createOrder(student, loan, orderItem1, orderItem2);
            em.persist(order);

        }

        public void dbInit2() {
            Student student = new Student();
            student.setName("park");
            em.persist(student);

            Book book1 = new Book();
            book1.setName("C++");
            book1.setStockQuantity(40);
            em.persist(book1);

            Book book2 = new Book();
            book2.setName("Python");
            book2.setStockQuantity(15);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 3);

            Loan loan = new Loan();
            Order order = Order.createOrder(student, loan, orderItem1, orderItem2);
            em.persist(order);


        }
    }

}
