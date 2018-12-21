package somrat.info.japhibernate.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import somrat.info.japhibernate.Entity.Course;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class JPQLTest {
    @Autowired
    EntityManager entityManager;

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
}
