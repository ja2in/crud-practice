package jaein.crudpractice.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@DiscriminatorValue("Book")
public class Book extends Item{

    private String author;
    private String isbn;

    public Book(String author, String isbn) {
        this.author = author;
        this.isbn = isbn;
    }


}
