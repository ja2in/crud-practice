package jaein.crudpractice.repository;

import jaein.crudpractice.domain.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Student student){
        em.persist(student);
    }

    public Student findOne(Long id){
        return em.find(Student.class, id);
    }

    public List<Student> findAll(){
        return em.createQuery("select s from Student s", Student.class)
                .getResultList();
    }

    public List<Student> findByName(String name){
        return em.createQuery("select s from Student s where s.name = :name", Student.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Student> findByNum(int num){
        return em.createQuery("select s from Student s where s.num = :num", Student.class)
                .setParameter("num", num)
                .getResultList();
    }

    public List<Student> findByDept(String dept){
        return em.createQuery("select s from Student s where s.dept = :dept", Student.class)
                .setParameter("dept", dept)
                .getResultList();
    }

}
