package somrat.info.japhibernate.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import somrat.info.japhibernate.Entity.*;

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

    public List<PartTimeEmployee> retrieveAllPartTImeEmployees() {
        return entityManager.createQuery("select p from PartTimeEmployee p", PartTimeEmployee.class)
                .getResultList();
    }

    public List<FullTimeEmployee> retrieveAllFullImeEmployees() {
        return entityManager.createQuery("select f from FullTimeEmployee f", FullTimeEmployee.class)
                .getResultList();
    }



}
