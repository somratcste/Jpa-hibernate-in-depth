package somrat.info.japhibernate.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(query = "select c from Course c", name = "QUERY_ALL_COURSES")
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "course")
    private List<Review>  reviews ;

    protected Course() {}

    public Course(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    @Override
    public String toString() {
        return name + reviews ;
    }
}
