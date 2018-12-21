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

    @ManyToMany
    @JoinTable(name = "course_student",
        joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name ="student_id")
    )
    private List<Student> students;

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

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    @Override
    public String toString() {
        return name + reviews ;
    }
}
