package somrat.info.japhibernate.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import somrat.info.japhibernate.Entity.Course;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

}
