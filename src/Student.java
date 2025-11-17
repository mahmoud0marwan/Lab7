import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends User{
     private List<String> enrolledCourses= new ArrayList<>();
     private Map<String,Integer> progress=new HashMap<>();
     private static CourseManager courseManager;
     Student(String userId,String username,String email,String password)
     {
         super();
         this.role="Student";
         this.userId=userId;
         this.username=username;
         this.email=email;

         this.passwordHash= AuthManager.hashPassword(password);    //a method from class AuthManager

     }
    public static void setCourseManager(CourseManager courseManager) {
        Student.courseManager = courseManager;
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


    List<Course> viewEnrolledCourses()
    {
        List<Course> courses=courseManager.getAllCourses();
        List<Course> enrolled=new ArrayList<>();
        for(int i=0;i<courses.size();i++)
        {
            if(courses.get(i).isEnrolled(this.userId))
                enrolled.add(courses.get(i));
        }
        return enrolled;
    }


    public String getEnrolledCourses() {
        if (enrolledCourses == null || enrolledCourses.isEmpty()) {
            return "";
        }
        return String.join(", ", enrolledCourses);
    }

    public Map<String, Integer> getProgress() {
        return progress;
    }

}
