import java.util.ArrayList;
import java.util.List;

public class Instructor extends User {
    private List<String> createdCourses = new ArrayList<>();
    private CourseManager courseManager;

    public Instructor(String userId, String username, String email, String password, CourseManager courseManager) {
        super();
        this.courseManager = courseManager;
        this.role = "Instructor";
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.passwordHash = AuthManager.hashPassword(password);
    }

    public String createCourse(String title, String description) {
        String courseId = courseManager.createCourse(this.userId, title, description);
        if (courseId != null) {
            createdCourses.add(courseId);
        }
        return courseId;
    }

    public void editCourse(String courseId, String title, String description) {
        Course course = courseManager.getCourseById(courseId);
        if (course == null) {
            throw new IllegalArgumentException("Course not found");
        }
        if (!course.getInstructorId().equals(this.userId)) {
            throw new IllegalArgumentException("You cannot edit this course");
        }

        if (title != null && !title.trim().isEmpty()) {
            course.setTitle(title.trim());
        }
        if (description != null && !description.trim().isEmpty()) {
            course.setDescription(description.trim());
        }

        courseManager.updateCourse(course);
    }

    public void addLesson(String courseId, Lesson lesson) {
        courseManager.addLesson(courseId, lesson);
    }

    public void editLesson(String courseId, Lesson updatedLesson) {
        courseManager.editLesson(courseId, updatedLesson);
    }

    public void deleteLesson(String courseId, String lessonId) {
        courseManager.removeLesson(courseId, lessonId);
    }

    public List<Student> viewEnrolledStudents(String courseId) {
        Course course = courseManager.getCourseById(courseId);
        if (course == null) {
            throw new IllegalArgumentException("Course not found");
        }
        if (!course.getInstructorId().equals(this.userId)) {
            throw new IllegalArgumentException("You cannot view students for this course");
        }
        return course.getStudents();
    }

    public String getRole() {
        return this.role;
    }

    public List<String> getCreatedCourses() {
        return new ArrayList<>(createdCourses);
    }
}