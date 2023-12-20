거package jaein.crudpractice.service;

import jaein.crudpractice.domain.Order;
import jaein.crudpractice.domain.OrderStatus;
import jaein.crudpractice.domain.Student;
import jaein.crudpractice.domain.item.Book;
import jaein.crudpractice.domain.item.Item;
import jaein.crudpractice.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 도서대여() throws Exception {
        //given
        Student student = new Student(1, "kim", "information");
        Item book = createBook("jaein", "1234", 10);
        int orderCount = 3;

        //when
        Long orderId = orderService.order(student.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);


        assertEquals(OrderStatus.CAN, getOrder.getStatus(), "도서대여 가능 상태");
        assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 일치");
        assertEquals(7, book.getStockQuantity(), "주문 수량만큼 재고 감소");
    }

    private Item createBook(String author, String isbn, int stockQuantity) {
        Book book = new Book();
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    @Test
    public void 도서대출수량초과() throws Exception{
        //given
        Student student = new Student(1, "kim", "information");
        Item book = createBook("jaein", "1234", 10);

        int orderCount = 15;

        //when

        //then
        assertThrows(IllegalStateException.class,
                () -> orderService.order(student.getId(), book.getId(), orderCount));

    }

    @Test
    public void 주문취소() throws Exception{
        //given
        Student student = new Student(1, "kim", "information");
        Item book = createBook("jaein", "1234", 10);

        int orderCount = 3;

        Long orderId = orderService.order(student.getId(), book.getId(), orderCount);//주문신청

        //when
        orderService.cancelOrder(orderId);
        Order getOrder = orderRepository.findOne(orderId);

        //then
        assertEquals(OrderStatus.CANT, getOrder.getStatus(), "주문취소시 상태는 CANT");
        assertEquals(10, book.getStockQuantity(), "주문취소시 재고 증가");

    }

}