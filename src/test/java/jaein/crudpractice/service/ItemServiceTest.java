package jaein.crudpractice.service;

import jaein.crudpractice.domain.item.Book;
import jaein.crudpractice.domain.item.Item;
import jaein.crudpractice.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;


    @Test
    @BeforeEach
    public void 상품저장() throws Exception {
        Item b1 = new Book("lee", "1234");
        Item b2 = new Book("kim", "5678");
        Item b3 = new Book("park", "1357");

        b1.setName("책1");
        b2.setName("책2");
        b3.setName("책3");

        b1.setStockQuantity(10);
        b2.setStockQuantity(20);
        b3.setStockQuantity(30);

        itemService.saveItem(b1);
        itemService.saveItem(b2);
        itemService.saveItem(b3);

    }

    @Test
    @Rollback(value = false)
    public void 상품수정() throws Exception{

        itemService.updateItem(1L, "책1 수정", 40);
        itemService.updateItem(2L, "책2 수정", 50);
        itemService.updateItem(3L, "책3 수정", 60);

    }

    @Test
    public void 상품찾기() throws Exception{
        List<Item> items = itemService.findItems();
        for (Item item : items) {
            System.out.println("item = " + item);
        }

        Item one = itemService.findOne(1L);
        System.out.println("one = " + one);


    }

}