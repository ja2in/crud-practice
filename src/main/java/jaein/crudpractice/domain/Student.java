package jaein.crudpractice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Student {

    @Id @GeneratedValue
    @Column(name = "student_id")
    private Long id;

    private Long num; //학번

    private String name; //이름

    private String dept; //과

    private List<Order> orders = new ArrayList<>();

    public Student() {
    }
}
