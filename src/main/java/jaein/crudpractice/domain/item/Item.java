package jaein.crudpractice.domain.item;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter @Setter
@DiscriminatorColumn(name = "dtype")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();


    public void removeStock(int count){
        int rest = this.stockQuantity -= count;
        if(rest < 0){
            throw new IllegalStateException("no such quantity");
        }
        this.stockQuantity = rest;
    }
    public void addStock(int count) {
        this.stockQuantity += count;
    }
}
