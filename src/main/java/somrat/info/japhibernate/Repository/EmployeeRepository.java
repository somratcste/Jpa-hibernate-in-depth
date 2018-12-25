package somrat.info.japhibernate.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import somrat.info.japhibernate.Entity.Course;
import somrat.info.japhibernate.Entity.Employee;
import somrat.info.japhibernate.Entity.Review;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    public void insert(Employee employee) {
        entityManager.persist(employee);
    }

    public List<Employee> retrieveAllEmployees() {
        return entityManager.createQuery("select e from Employee e", Employee.class)
                .getResultList();
    }



}
