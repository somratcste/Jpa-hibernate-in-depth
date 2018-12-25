package somrat.info.japhibernate.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import somrat.info.japhibernate.Entity.Course;
import somrat.info.japhibernate.Entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class JPQLTest {
    @Autowired
    EntityManager entityManager;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void basic() {
        Query query = entityManager.createQuery("SELECT c FROM Course c");
        System.out.println(query.getResultList());
    }

    @Test
    public void basic_typed() {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c", Course.class);
        List<Course> resultList = query.getResultList();
        System.out.println(resultList);
    }

    @Test
    public void basic2() {
        Query query = entityManager.createQuery("SELECT c FROM Course c WHERE c.name like '%100 Steps'");
        List resultList = query.getResultList();
        assertEquals(2, resultList.size());
        System.out.println(resultList);
    }

    @Test
    public void basic_empty_courses() {
        Query query = entityManager.createQuery("SELECT c FROM Course c WHERE c.students IS EMPTY");
        List resultList = query.getResultList();
        assertEquals(1, resultList.size());
        System.out.println(resultList);
    }

    @Test
    public void basic_courses_with_min_three_students() {
        Query query = entityManager.createQuery("SELECT c FROM Course c WHERE size(c.students) >= 3");
        List resultList = query.getResultList();
        assertEquals(1, resultList.size());
        System.out.println(resultList);
    }

    @Test
    public void jpql_basic() {
        Query query = entityManager.createNamedQuery("query_get_all_courses");
        List resultList = query.getResultList();
        logger.info("Select  c  From Course c -> {}", resultList);
    }

    @Test
    public void jpql_typed() {
        TypedQuery<Course> query = entityManager.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select  c  From Course c -> {}", resultList);
    }

    @Test
    public void jpql_where() {
        TypedQuery<Course> query = entityManager.createNamedQuery("query_get_100_Step_courses", Course.class);
        List<Course> resultList = query.getResultList();

        logger.info("Select  c  From Course c where name like '%100 Steps'-> {}", resultList);
        // [Course[Web Services in 100 Steps], Course[Spring Boot in 100 Steps]]
    }

    @Test
    public void jpql_courses_without_students() {
        TypedQuery<Course> query = entityManager.createQuery("Select c from Course c where c.students is empty", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
        // [Course[Spring in 50 Steps]]
    }

    @Test
    public void jpql_courses_with_atleast_2_students() {
        TypedQuery<Course> query = entityManager.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
        //[Course[JPA in 50 Steps]]
    }

    @Test
    public void jpql_courses_ordered_by_students() {
        TypedQuery<Course> query = entityManager.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }

    @Test
    public void jpql_students_with_passports_in_a_certain_pattern() {
        TypedQuery<Student> query = entityManager.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);
        List<Student> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }

    //like
    //BETWEEN 100 and 1000
    //IS NULL
    //upper, lower, trim, length

    //JOIN => Select c, s from Course c JOIN c.students s
    //LEFT JOIN => Select c, s from Course c LEFT JOIN c.students s
    //CROSS JOIN => Select c, s from Course c, Student s
    //3 and 4 =>3 * 4 = 12 Rows

    @Test
    public void join(){
        Query query = entityManager.createQuery("Select c, s from Course c JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());
        for(Object[] result:resultList){
            logger.info("Course{} Student{}", result[0], result[1]);
        }
    }

    @Test
    public void left_join(){
        Query query = entityManager.createQuery("Select c, s from Course c LEFT JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());
        for(Object[] result:resultList){
            logger.info("Course{} Student{}", result[0], result[1]);
        }
    }

    @Test
    public void cross_join(){
        Query query = entityManager.createQuery("Select c, s from Course c, Student s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());
        for(Object[] result:resultList){
            logger.info("Course{} Student{}", result[0], result[1]);
        }
    }

}
