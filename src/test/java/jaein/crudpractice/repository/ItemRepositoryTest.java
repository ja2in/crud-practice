package jaein.crudpractice.repository;

import jaein.crudpractice.domain.item.Book;
import jaein.crudpractice.domain.item.Item;
import jaein.crudpractice.domain.item.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
//    @Rollback(value = false)
    public void saveTest() throws Exception{
        //given
        Book book = new Book("kim", "1234");
        itemRepository.save(book);
        //when

        //then
    }

    @Test
//    @Rollback(value = false)
    public void findByAuthorTest() throws Exception{
        //given
        Book book = new Book("kim", "1234");
        itemRepository.save(book);

        //when
        List<Book> result = itemRepository.findByAuthor("kim");
        Book findBook = result.get(0);

        //then
        assertThat(findBook).isEqualTo(book);
//        assertThat(kim).contains(book);
        System.out.println("result = " + result.get(0).getIsbn());
        System.out.println("findBook = " + findBook.getIsbn());
    }

    @Test
    @Rollback(value = false)
    public void findByIsbn() throws Exception{
        //given
        Book b1 = new Book("kim", "1234");
        Book b2 = new Book("park", "5678");
        Book b3 = new Book("lee", "1357");

        itemRepository.save(b1);
        itemRepository.save(b2);
        itemRepository.save(b3);

        //when
        List<Book> result = itemRepository.findByIsbn("1357");
        Book findBook = result.get(0);

        //then
        assertThat(findBook).isEqualTo(b3);
        System.out.println("result = " + result.get(0).getAuthor());
        System.out.println("findBook = " + findBook.getIsbn());
    }

}