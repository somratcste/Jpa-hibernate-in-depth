package somrat.info.japhibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import somrat.info.japhibernate.Entity.Course;
import somrat.info.japhibernate.Repository.CourseRepository;
import somrat.info.japhibernate.Repository.StudentRepository;


@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaHibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        studentRepository.saveStudentWithPassport();
    }

}

