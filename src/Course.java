import java.util.ArrayList;
import java.util.List;

public class Course {

    String courseId;
    String instructorId;
    String title;
    String description;
    List<Lesson> lessons;
    List<Student> students;
    private static StudentManager studentManager;
    public Course(String courseId, String instructorId, String title, String description) {
        this.courseId = courseId;
        this.instructorId = instructorId;
        this.title = title;
        this.description = description;
        this.lessons = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public static void setStudentManager(StudentManager studentManager) {
        Course.studentManager = studentManager;
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
        studentManager.enrollInCourse(studentId,this.courseId);
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

    public void setTitle(String title) {
        this.title=title;
    }

    public void setDescription(String description) {
        this.description=description;
    }
    public boolean isEnrolled(String studentId)
    {
        for(int i=0;i<students.size();i++)
        {
            if (students.get(i).getUserId()==studentId)
                return true;
        }
        return false;
    }
}
