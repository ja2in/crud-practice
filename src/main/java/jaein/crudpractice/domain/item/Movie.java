package jaein.crudpractice.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@DiscriminatorValue("Movie")
public class Movie extends Item{

    private String director;

    public Movie(String director) {
        this.director = director;
    }
}
