package somrat.info.japhibernate.Repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import somrat.info.japhibernate.Entity.Course;
import somrat.info.japhibernate.JpaHibernateApplication;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateApplication.class)
public class CourseSpringDataRepositoryTests {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseSpringDataRepository courseSpringDataRepository;

    @Test
    public void findById_CoursePresent() {
       Optional<Course> courseOptional =  courseSpringDataRepository.findById(10001L);
        assertTrue(courseOptional.isPresent());
    }

    @Test
    public void findById_CourseNotPresent() {
        Optional<Course> courseOptional = courseSpringDataRepository.findById(20001L);
        assertFalse(courseOptional.isPresent());
    }

    @Test
    public void playingAroundWithSpringDataRepository() {
        //Course course = new Course("Microservices in 100 Steps");
        //repository.save(course);

        //course.setName("Microservices in 100 Steps - Updated");
        //repository.save(course);
        logger.info("Courses -> {} ", courseSpringDataRepository.findAll());
        logger.info("Count -> {} ", courseSpringDataRepository.count());
    }

    @Test
    public void sort() {
        Sort sort = new Sort(Sort.Direction.ASC, "name");
        logger.info("Sorted Courses -> {} ", courseSpringDataRepository.findAll(sort));
        //Courses -> [Course[JPA in 50 Steps], Course[Spring in 50 Steps], Course[Spring Boot in 100 Steps]]
    }

    @Test
    public void pagination() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Course> firstPage = courseSpringDataRepository.findAll(pageRequest);
        logger.info("First Page -> {} ", firstPage.getContent());

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = courseSpringDataRepository.findAll(secondPageable);
        logger.info("Second Page -> {} ", secondPage.getContent());
    }

    @Test
    public void findUsingName() {
        logger.info("FindByName -> {} ", courseSpringDataRepository.findByName("JPA in 50 Steps"));
    }

    @Test
    public void findUsingStudentsName() {
        logger.info("findUsingStudentsName -> {} ", courseSpringDataRepository.findByName("Ranga"));
    }
}

