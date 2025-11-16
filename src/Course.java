import java.util.ArrayList;
import java.util.List;

public class Course {

    String courseId;
    String instructorId;
    String title;
    String description;
    List<Lesson> lessons;
    List<Student> students;

    public Course(String courseId, String instructorId, String title, String description) {
        this.courseId = courseId;
        this.instructorId = instructorId;
        this.title = title;
        this.description = description;
        this.lessons = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void updateLesson(Lesson lesson) {
        for (int i = 0; i < lessons.size(); i++) {
            if (lessons.get(i).getLessonId().equals(lesson.getLessonId())) {
                lessons.set(i, lesson);
            }
        }
    }

    public void deleteLesson(String lessonId) {
        lessons.removeIf(lesson -> lesson.getLessonId().equals(lessonId));
    }

    public void enrollStudent(String studentId) {
        for (Student s : students) {
            if (s.getUserId().equals(studentId)) return ;
        }
        students.add(new Student(studentId));
    }

    public Lesson getLessonById(String lessonId) {
        for (Lesson lesson : lessons) {
            if (lesson.getLessonId().equals(lessonId))
                return lesson;
        }
        return null;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<Student> getStudents() {
        return students;
    }
}
