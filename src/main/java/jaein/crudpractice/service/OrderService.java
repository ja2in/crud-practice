package jaein.crudpractice.service;

import jaein.crudpractice.domain.*;
import jaein.crudpractice.domain.item.Item;
import jaein.crudpractice.repository.ItemRepository;
import jaein.crudpractice.repository.OrderRepository;
import jaein.crudpractice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final StudentRepository studentRepository;
    private final ItemRepository itemRepository;

    public Long order(Long studentId, Long itemId, int count){
        //엔티티 조회
        Student student = studentRepository.findOne(studentId);
        Item item = itemRepository.findOne(itemId);

        //대여 정보 조회
        Loan loan = new Loan();
        loan.setState(LoanState.O); //대여 가능

        //OrderItem 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, count);

        //Order 생성
        Order order = Order.createOrder(student, loan, orderItem);

        //주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    //주문 취소
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }
}
