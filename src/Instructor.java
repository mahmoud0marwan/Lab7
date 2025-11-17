import java.util.ArrayList;
import java.util.List;

public class Instructor extends User {

    private List<String>createdCourses=new ArrayList<>();

    JsonDatabaseManager j=new JsonDatabaseManager("users.json","courses.json");
    CourseManager courseManager=new CourseManager(j);

    Instructor(String userId,String username,String email,String password)
    {
        super();
        this.role="Instructor";
        this.userId=userId;
        this.username=username;
        this.email=email;
        this.passwordHash= AuthManager.hashPassword(password);    //a method from class AuthManager
    }


    public String createCourse(String title, String description,JsonDatabaseManager j) {
        String courseId;
        do {
            courseId = IdGenerator.generateCourseId();
        }
        while (!j.isUserIdUnique(courseId));

        Course course = new Course(courseId, this.userId, title, description);
        createdCourses.add(courseId);
        CourseManager.courses.add(course);
        j.saveCourses(CourseManager.courses);
        return courseId;

    }
    public void editCourse(String courseId,String title, String description,JsonDatabaseManager j)
    {
        Course c=CourseManager.getCourseById(courseId);
        if (c.getInstructorId().equals(this.userId))
        {
            if (title != null){
                c.setTitle(title);
                j.saveCourses(CourseManager.courses);
            } else if (description != null) {
                c.setDescription(description);
                j.saveCourses(CourseManager.courses);
            }
            else
            {
                c.setTitle(title);
                c.setDescription(description);
                j.saveCourses(CourseManager.courses);
            }
        }
        else
            throw new IllegalArgumentException("You can not edit this course");
    }
    public void addLesson(String courseId, Lesson lesson)
    {
      Course.addLesson(courseId,lesson);
    }
    public void editLesson(String courseId, Lesson updatedLesson)
    {
      Course.editLesson(courseId,updatedLesson); //this method should save
    }
    public void deleteLesson(String courseId, String lessonId)
    {
     Course.removeLesson(courseId,lessonId);  //this method should save
    }
    List<Student> viewEnrolledStudents(String courseId)
    {
        Course c=CourseManager.getCourseById(courseId);
        return c.getStudents;
    }
    String getRole()
    {
        return this.role;
    }
}
