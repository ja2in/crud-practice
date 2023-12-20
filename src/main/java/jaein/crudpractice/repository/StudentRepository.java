package jaein.crudpractice.repository;

import jaein.crudpractice.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s where s.name = :name")
    List<Student> findByName(@Param("name") String name);

    @Query("select s from Student s where s.num = :num")
    List<Student> findByNum(@Param("num") int num);

    @Query("select s from Student s where s.dept = :dept")
    List<Student> findByDept(@Param("dept") String dept);

    @Query("select s from Student s where s.name = :name and s.num = :num")
    List<Student> findUser(@Param("name") String name, @Param("num") int num);

    @Query("select s from Student s where s.id = :id")
    Student findOne(@Param("id") Long id);
}
