package somrat.info.japhibernate.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "QUERY_ALL_COURSES",
                query = "select c from Course c" ),
        @NamedQuery(name = "query_get_all_courses",
                query = "Select  c  From Course c"),
        @NamedQuery(name = "query_get_all_courses_join_fetch",
                query = "Select  c  From Course c JOIN FETCH c.students s"),
        @NamedQuery(name = "query_get_100_Step_courses",
                query = "Select  c  From Course c where name like '%100 Steps'") })

@SQLDelete(sql = "update course set is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Column(columnDefinition="tinyint(1) default 0")
    private boolean isDeleted;

    @OneToMany(mappedBy = "course")
    private List<Review>  reviews ;

    @ManyToMany
    @JoinTable(name = "course_student",
        joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name ="student_id")
    )
    private List<Student> students = new ArrayList<>();

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

    @PreRemove
    public void preRemove() {
        this.isDeleted = true;
    }

    @Override
    public String toString() {
        return String.format("Course[%s]", name);
    }
}
