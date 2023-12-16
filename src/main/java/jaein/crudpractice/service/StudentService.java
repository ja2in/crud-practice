package jaein.crudpractice.service;

import jaein.crudpractice.domain.Student;
import jaein.crudpractice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public Long join(Student student){
        validateDuplicateStudent(student); //중복 조회
        studentRepository.save(student);
        return student.getId();
    }
    private void validateDuplicateStudent(Student student) {
        List<Student> findStudent = studentRepository.findByName(student.getName());
        if(!findStudent.isEmpty()){
            throw new IllegalStateException("이미 존재하는 학생입니다.");
        }
    }

    public List<Student> findStudents(){
        return studentRepository.findAll();
    }

    public Student findOne(Long studentId){
//        return studentRepository.findById(studentId);
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new IllegalArgumentException("no such student"));

        return student;
    }
}
