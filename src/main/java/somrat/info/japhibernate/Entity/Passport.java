package somrat.info.japhibernate.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Passport {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String number;

    protected Passport() {}

    public Passport(Long id, String number) {
        this.id = id;
        this.number = number;
    }

    public Passport(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number ;
    }
}
