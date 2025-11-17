import java.util.List;

public class StudentManager {

    public final JsonDatabaseManager db;
    private final CourseManager courseManager;

    public StudentManager(JsonDatabaseManager db, CourseManager courseManager) {
        this.db = db;
        this.courseManager = courseManager;
    }

    public List<Course> browseCourses() {
        return db.loadCourses();
    }

    public void enrollInCourse(String studentId, String courseId) {

        User user = db.getUserById(studentId);
        if (user == null || !(user instanceof Student)) {
            throw new RuntimeException("Student " + studentId +" not found" + studentId);
        }
        Student student = (Student) user;

        Course course = courseManager.getCourseById(courseId);
        if (course == null) {
            throw new RuntimeException("Course " + courseId +" not found" );
        }

        if (student.getEnrolledCourses().contains(courseId)) {
            return;
        }

        student.enrollCourse(courseId);
        student.getProgress().put(courseId, 0);

        course.enrollStudent(studentId);

        db.saveUsers(db.loadUsers());
        db.saveCourses(db.loadCourses());
    }

    public void markLessonCompleted(String studentId, String courseId, String lessonId) {

        User user = db.getUserById(studentId);
        if (user == null || !(user instanceof Student)) {
            throw new RuntimeException("Student not found");
        }
        Student student = (Student) user;

        Course course = courseManager.getCourseById(courseId);
        if (course == null) {
            throw new RuntimeException("Course not found");
        }

        if (!student.getEnrolledCourses().contains(courseId)) {
            throw new RuntimeException("Student not enrolled in this course");
        }

        Lesson lesson = course.getLessonById(lessonId);
        if (lesson == null) {
            throw new RuntimeException("Lesson " + lessonId+" not found" );
        }

        int completedLessons = student.getProgress().getOrDefault(courseId,0);

        student.getProgress().put(courseId, completedLessons + 1);

        List<User> users = db.loadUsers();
        db.saveUsers(users);

    }
}
