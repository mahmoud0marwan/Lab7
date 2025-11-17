import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends User{
     private List<String> enrolledCourses= new ArrayList<>();
     private Map<String,Integer> progress=new HashMap<>();
     private CourseManager courseManager;
     Student(String userId,String username,String email,String password,CourseManager courseManager)
     {
         super();
         this.role="Student";
         this.userId=userId;
         this.username=username;
         this.email=email;
         this.courseManager = courseManager;
         this.passwordHash= AuthManager.hashPassword(password);    //a method from class AuthManager

     }
    public void setCourseManager(CourseManager courseManager) {
        this.courseManager = courseManager;
    }
     public void enrollCourse(String courseId)
     {
         if(courseId==null||courseId.trim().isEmpty())
             throw new IllegalArgumentException("Course ID is invalid");
         if(enrolledCourses.contains(courseId))
             throw new IllegalArgumentException("Student is already enrolled in this course");
         enrolledCourses.add(courseId);
         progress.put(courseId,0);
     }
     public void updateProgresss(String courseId,int lessonIndex)
     {
         if (courseId == null || courseId.trim().isEmpty()) {
             throw new IllegalArgumentException("Course ID cannot be null or empty.");
         }
         if (!enrolledCourses.contains(courseId)) {
             throw new IllegalArgumentException("Student is not enrolled in this course: " + courseId);
         }
         if (lessonIndex < 0) {
             throw new IllegalArgumentException("Lesson index cannot be negative.");
         }
         Integer currentProgress = progress.get(courseId);
         if (currentProgress == null) {
             progress.put(courseId, lessonIndex);
             return;
         }
         if (lessonIndex < currentProgress) {
             throw new IllegalArgumentException(
                     "Cannot decrease progress. Current progress is lesson " + currentProgress + "."
             );
         }
         progress.put(courseId,lessonIndex);
     }
    public String getRole()
    {
        return this.role;
    }


    List<Course> viewEnrolledCourses() {
        List<Course> allCourses = courseManager.getAllCourses();
        List<Course> enrolled = new ArrayList<>();
        for (Course course : allCourses) {
            if (course.isEnrolled(this.userId))
                enrolled.add(course);
        }
        return enrolled;
    }


    public List<String> getEnrolledCourses() {
        if (enrolledCourses == null || enrolledCourses.isEmpty()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(enrolledCourses);
    }

    public Map<String, Integer> getProgress() {
        return progress;
    }

}
