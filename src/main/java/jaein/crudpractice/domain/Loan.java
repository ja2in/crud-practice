package jaein.crudpractice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
public class Loan {

    @Id @GeneratedValue
    @Column(name = "loan_id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "loan", fetch = FetchType.LAZY)
    private Order order;

    @Enumerated(value = EnumType.STRING)
    private LoanState state;    //O, X
}
