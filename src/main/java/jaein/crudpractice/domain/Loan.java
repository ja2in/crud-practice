package jaein.crudpractice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Loan {

    @Id @GeneratedValue
    @Column(name = "loan_id")
    private Long id;

    private Order order;

    private LoanState state;
}
