package somrat.info.japhibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import somrat.info.japhibernate.Entity.*;
import somrat.info.japhibernate.Repository.CourseRepository;
import somrat.info.japhibernate.Repository.EmployeeRepository;
import somrat.info.japhibernate.Repository.StudentRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaHibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        studentRepository.saveStudentWithPassport();
//        courseRepository.addHardCodedReviewsForCourse();
//        List<Review> reviews = new ArrayList<>();
//        reviews.add(new Review("5", "Great Hands of staff"));
//        reviews.add(new Review("4", "Hats Off"));
//        courseRepository.addReviewsForCourse(10003L, reviews);

//        studentRepository.insertHardCodeStudentAndCourse();

        employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
        employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));
        logger.info("AllEmployees -> {}" , employeeRepository.retrieveAllEmployees());
    }

}

