package jaein.crudpractice.api;

import jaein.crudpractice.domain.Student;
import jaein.crudpractice.service.StudentService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class StudentApiController {

    private final StudentService studentService;

//    @PostMapping("/api/v1/students")
//    public CreateStudentResponse saveStudentV1(@RequestBody @Valid Student student){
//        Long id = studentService.join(student);
//        return new CreateStudentResponse(id);
//    }

    @PostMapping("/api/v2/students")    //등록
    public CreateStudentResponse saveStudentV2(@RequestBody @Valid CreateStudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());

        Long id = studentService.join(student);
        return new CreateStudentResponse(id);
    }

    @PutMapping("/api/v2/students/{id}")    //수정
    public UpdateStudentResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateStudentRequest request) {

        studentService.update(id, request.getName());
        Student findStudent = studentService.findOne(id);
        return new UpdateStudentResponse(findStudent.getId(), findStudent.getName());
    }

//    @GetMapping("/api/v1/students") //조회
//    public List<Student> studentsV1(){
//        return studentService.findStudents();
//    }

    @GetMapping("/api/v2/students")
    public Result studentsV2(){
        List<Student> findStudents = studentService.findStudents();
        List<StudentDto> collect = findStudents.stream()
                .map(m -> new StudentDto(m.getName()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{ //유연성 문제
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class StudentDto{
        private String name;
    }

    @Data
    static class UpdateStudentRequest{  //DTO
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateStudentResponse{
        private Long id;
        private String name;
    }

    @Data
    static class CreateStudentRequest { //DTO
        private String name;
    }


    @Data
    static class CreateStudentResponse {
        private Long id;

        public CreateStudentResponse(Long id) {
            this.id = id;
        }
    }
}
