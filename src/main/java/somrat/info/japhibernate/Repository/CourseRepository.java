package somrat.info.japhibernate.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import somrat.info.japhibernate.Entity.Course;

import javax.persistence.EntityManager;


@Repository
public class CourseRepository {

    @Autowired
    EntityManager entityManager;

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }
}
