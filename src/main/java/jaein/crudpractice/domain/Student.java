package jaein.crudpractice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id @GeneratedValue
    @Column(name = "student_id")
    private Long id;

    private Long num; //학번

    private String name; //이름

    private String dept; //과

    @OneToMany(mappedBy = "student")
    private List<Order> orders = new ArrayList<>();

}
