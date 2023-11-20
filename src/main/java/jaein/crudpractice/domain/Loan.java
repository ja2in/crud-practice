package jaein.crudpractice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Loan {

    @Id @GeneratedValue
    @Column(name = "loan_id")
    private Long id;

    @OneToOne(mappedBy = "loan", fetch = FetchType.LAZY)
    private Order order;

    @Enumerated(value = EnumType.STRING)
    private LoanState state;    //CAN, CANT
}
