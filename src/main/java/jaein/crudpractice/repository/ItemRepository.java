package jaein.crudpractice.repository;

import jaein.crudpractice.domain.item.Book;
import jaein.crudpractice.domain.item.Item;
import jaein.crudpractice.domain.item.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    /**
     * Book Query
     */
    @Query("select b from Book b where author = :author")
    List<Book> findByAuthor(@Param("author") String author);
    @Query("select b from Book b where isbn = :isbn")
    List<Book> findByIsbn(@Param("isbn") String isbn);

    /**
     * Movie Query
     */
    @Query("select m from Movie m where director = :director")
    List<Movie> findByDirector(@Param("director") String director);

    @Query("select i from Item i where i.id = :id")
    Item findOne(@Param("id") Long id);
}
