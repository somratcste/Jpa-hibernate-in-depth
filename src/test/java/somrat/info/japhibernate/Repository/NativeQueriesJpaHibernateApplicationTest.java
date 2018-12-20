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
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NativeQueriesJpaHibernateApplicationTest {

    @Autowired
    EntityManager entityManager;

    @Test
    public void basic() {
        Query query = entityManager.createNativeQuery("SELECT * FROM Course c");
        System.out.println(query.getResultList());
    }

    @Test
    public void basic_with_parameter() {
        Query query = entityManager.createNativeQuery("SELECT * FROM Course c where c.id = ?");
        query.setParameter(1  , 10001L);

        List resultList = query.getResultList();
        assertEquals(1,resultList.size());

        System.out.println(resultList);
    }

    @Test
    public void basic_with_named_parameter() {
        Query query = entityManager.createNativeQuery("SELECT * FROM Course c where c.id = :id");
        query.setParameter("id", 10001L);

        List resultList = query.getResultList();
        assertEquals(1,resultList.size());

        System.out.println(resultList);
    }


    @Test
    @Transactional
    public void updating_a_number_of_rows() {
        Query query = entityManager.createNativeQuery("Update Course Set create_date_time=sysdate()");
        int executeUpdate = query.executeUpdate();
        System.out.println(executeUpdate);
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



}
