package jaein.crudpractice.service;

import jaein.crudpractice.domain.Student;
import jaein.crudpractice.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StudentServiceTest {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;

    @Test
    public void 회원가입() throws Exception{
        //given
        Student student = new Student();
        student.setName("kim");

        //when
        Long join = studentService.join(student);

        //then
        assertEquals(student, studentRepository.findById(join).orElse(null));
    }

    @Test
    public void 중복회원가입() throws Exception{
        //given
        Student s1 = new Student();
        s1.setName("park");

        Student s2 = new Student();
        s2.setName("park");

        //when
//        studentService.join(s1);
//        studentService.join(s2);

        studentService.join(s1);
        try {
            studentService.join(s2);
        }catch (IllegalStateException e){
            return ;
        }

        //then
        fail("예외가 발생했습니다.");
    }
}