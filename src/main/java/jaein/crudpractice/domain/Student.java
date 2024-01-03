package jaein.crudpractice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Student {

    @Id @GeneratedValue
    @Column(name = "student_id")
    private Long id;

    private int num; //학번

    @NotEmpty
    private String name; //이름

    private String dept; //과

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Order> orders = new ArrayList<>();

    public Student(int num, String name, String dept) {
        this.num = num;
        this.name = name;
        this.dept = dept;
    }

    public Student() {
    }
}
