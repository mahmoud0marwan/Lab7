import java.util.ArrayList;
import java.util.List;

public class CourseManager {

    private List<Course> courses;

    public CourseManager(JsonDatabaseManager j) {
        this.courses = j.loadCourses();
    }

    public Course getCourseById(String id) {
        for (Course c : courses) {
            if (c.getCourseId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public String createCourse(String courseId , String instructorId , String title, String description) {
        if (getCourseById(courseId) != null) {
            return null;
        }

        Course newCourse = new Course(courseId, instructorId, title, description);
        courses.add(newCourse);
        return courseId;
    }

    public boolean updateCourse(Course updatedCourse) {
        for (int i=0 ; i<courses.size() ; i++){
            if (courses.get(i).getCourseId().equals(updatedCourse.getCourseId())) {
            courses.set(i,updatedCourse);
            return true;
            }
            }
        return false;
        }

        public boolean deleteCourse(String courseId) {
            return courses.removeIf(c -> c.getCourseId().equals(courseId));
        }

        public void addLesson(String courseId, Lesson lesson) {
            Course course = getCourseById(courseId);
            if (course != null) {
                course.addLesson(lesson);
            }
        }

        public void editLesson(String courseId, Lesson lesson) {
            Course course = getCourseById(courseId);
            if (course != null) {
                course.updateLesson(lesson);
            }
        }

        public void removeLesson(String courseId, String lessonId) {
            Course course = getCourseById(courseId);
            if (course != null) {
                course.deleteLesson(lessonId);
            }
        }
    public void addCourse(Course course)
    {
        this.courses.add(course);    }
    }
