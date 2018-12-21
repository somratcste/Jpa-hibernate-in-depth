package somrat.info.japhibernate.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import somrat.info.japhibernate.Entity.Course;
import somrat.info.japhibernate.Entity.Passport;
import somrat.info.japhibernate.Entity.Student;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StudentRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public Student save(Student Student) {
        if(Student.getId() == null) {
            entityManager.persist(Student);
        } else {
            entityManager.merge(Student);
        }

        return Student;
    }

    public void deleteById(Long id) {
        Student Student = findById(id);
        entityManager.remove(Student);
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("Z123456");
        entityManager.persist(passport);
        Student student = new Student("Mike");
        student.setPassport(passport);
        entityManager.persist(student);
    }


    public void insertHardCodeStudentAndCourse() {
        Student student = new Student("Jack");
        Course course = new Course("Microservice in 100 Steps");

        entityManager.persist(student);
        entityManager.persist(course);

        student.addCourse(course);
        course.addStudent(student);

        entityManager.persist(student);
    }

    public void insertStudentAndCourse(Student student, Course course) {

    }
}
