package jaein.crudpractice.api;

import jaein.crudpractice.domain.Order;
import jaein.crudpractice.domain.OrderStatus;
import jaein.crudpractice.repository.OrderRepository;
import jaein.crudpractice.repository.order.simplequery.OrderSimpleQueryDto;
import jaein.crudpractice.repository.order.simplequery.SimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final SimpleQueryRepository simpleQueryRepository;

    @GetMapping("/api/v1/simple-orders")    //엔티티 직접 노출 사용X
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAll();
        for (Order order : all) {
            order.getStudent().getName();   //강제 초기화
            order.getLoan().getState();
        }
        return all;
    }

//    @GetMapping("/api/v2/simple-orders")
//    public List<SimpleOrderDto> ordersV2(){
//        orderRepository.find
//    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3(){
        List<Order> orders = orderRepository.findAllWithStudentLoan();
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4(){
        return simpleQueryRepository.findOrderDtos();
    }

    @Data
    static class SimpleOrderDto{
        private Long orderId;
        private String name;
        private Date loanDate;
        private Date returnDate;
        private OrderStatus orderStatus;

        public SimpleOrderDto(Order order){
            orderId = order.getId();
            name = order.getStudent().getName();
            loanDate = order.getLoanDate();
            returnDate = order.getReturnDate();
            orderStatus = order.getStatus();
        }
    }
}
