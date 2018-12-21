package somrat.info.japhibernate.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import somrat.info.japhibernate.Entity.Course;
import somrat.info.japhibernate.Entity.Review;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    public Course save(Course course) {
        if(course.getId() == null) {
            entityManager.persist(course);
        } else {
            entityManager.merge(course);
        }

        return course;
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        entityManager.remove(course);
    }

    public void playWithEntityManager() {
//        Course course = new Course("Web Service in 100 Steps");
//        entityManager.persist(course);
//        entityManager.flush();
//
//        entityManager.clear();
//        entityManager.detach(course);
//
//        course.setName("Web Service in 100 Steps - Updated");
//        entityManager.flush();
    }

    public void addHardCodedReviewsForCourse() {
        Course course = findById(10003L);

        Review review1 = new Review("5","Great Hands On Staff");
        Review review2 = new Review("5","HatsOff");

        course.addReview(review1);
        review1.setCourse(course);

        course.addReview(review2);
        review2.setCourse(course);

        entityManager.persist(review1);
        entityManager.persist(review2);
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviewList) {
        Course course = findById(courseId);
        for(Review review: reviewList) {
            course.addReview(review);
            review.setCourse(course);
            entityManager.persist(review);
        }
    }
}
