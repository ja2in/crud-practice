package jaein.crudpractice.repository;

import jaein.crudpractice.domain.Student;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class StudentJpaRepositoryTest {

    @Autowired
    StudentJpaRepository studentRepository;

    @Autowired
    EntityManager em;

    @Test
//    @Rollback(value = false)
    public void saveTest() throws Exception{
        Student student1 = new Student(100, "kim", "A");
        Student student2 = new Student(200, "jung", "B");
        Student student3 = new Student(300, "park", "C");

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
    }

    @Test
    public void findTest() throws Exception{

        //given
        Student student = new Student(100, "kim", "A");
        studentRepository.save(student);

        //when
        Student one = studentRepository.findOne(student.getId());
        List<Student> all = studentRepository.findAll();
        List<Student> byName = studentRepository.findByName("kim");
        List<Student> byNum = studentRepository.findByNum(100);
        List<Student> byDept = studentRepository.findByDept("A");

        //then
        assertThat(one).isEqualTo(student);
        assertThat(all).containsExactly(student);
        assertThat(byName).containsExactly(student);
        assertThat(byNum).containsExactly(student);
        assertThat(byDept).containsExactly(student);
    }

}