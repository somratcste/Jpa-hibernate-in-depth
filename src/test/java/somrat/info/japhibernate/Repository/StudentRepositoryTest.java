package somrat.info.japhibernate.Repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import somrat.info.japhibernate.Entity.Course;
import somrat.info.japhibernate.Entity.Passport;
import somrat.info.japhibernate.Entity.Student;
import somrat.info.japhibernate.JpaHibernateApplication;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateApplication.class)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    public void retrieveStudentAndPassportDetails() {
        Student student = entityManager.find(Student.class, 20001L);
        logger.info("Value of Passport: {}", String.valueOf(student.getPassport()));
    }

    @Test
    @Transactional
    public void retrievePassportAndStudentDetails() {
        Passport passport = entityManager.find(Passport.class, 40001L);
        logger.info("Value of Student: {}", String.valueOf(passport.getStudent()));
    }

    @Test
    @Transactional
    public void retrieveStudentAndCourse() {
        Student student = entityManager.find(Student.class, 20001L);
        logger.info("Value of Student Courses: {}", String.valueOf(student.getCourses()));
    }

}

