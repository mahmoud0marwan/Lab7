public class Main {
    public static void main(String[] args) {

        JsonDatabaseManager db = new JsonDatabaseManager("users.json", "courses.json");
        CourseManager courseManager = new CourseManager(db);
        StudentManager studentManager = new StudentManager(db, courseManager);
        AuthManager authManager = new AuthManager(db);

        System.out.println("===== SIGNUP =====");
        Instructor ins = new Instructor("","","","");
        ins.setEmail("teacher@mail.com");
        ins.setUsername("Teacher");
        authManager.signup(ins, db);

        Student st = new Student("","","","");
        st.setEmail("student@mail.com");
        st.setUsername("Student");
        authManager.signup(st, db);

        System.out.println("Users saved.");

        System.out.println("\n===== LOGIN =====");
        User loggedIns = authManager.login("teacher@mail.com", "1234");
        System.out.println("Logged in as: " + loggedIns.getUsername());

        System.out.println("\n===== CREATE COURSE =====");
        String courseId = ((Instructor) loggedIns)
                .createCourse("Java Programming", "Learn Java from scratch", db);

        Course c = courseManager.getCourseById(courseId);
        System.out.println("Course created: " + c.getTitle());

        System.out.println("\n===== ADD LESSONS =====");
        Lesson l1 = new Lesson(IdGenerator.generateLessonId(), "Intro", "Welcome to Java");
        Lesson l2 = new Lesson(IdGenerator.generateLessonId(), "OOP", "Object-Oriented Programming");

        ((Instructor) loggedIns).addLesson(courseId, l1);
        ((Instructor) loggedIns).addLesson(courseId, l2);

        db.saveCourses(courseManager.getAllCourses());
        System.out.println("Lessons added.");

        System.out.println("\n===== STUDENT LOGIN =====");
        User loggedSt = authManager.login("student@mail.com", "1234");
        System.out.println("Logged in as: " + loggedSt.getUsername());

        System.out.println("\n===== ENROLL STUDENT =====");
        studentManager.enrollInCourse(loggedSt.getUserId(), courseId);
        System.out.println("Enrollment complete.");

        System.out.println("\n===== COMPLETE LESSON =====");
        studentManager.markLessonCompleted(loggedSt.getUserId(), courseId, l1.getLessonId());
        System.out.println("Lesson completed.");

        System.out.println("\n===== FINAL COURSE DATA =====");
        Course cc = db.getCourseById(courseId);
        System.out.println("Students in course: " + cc.getStudents().size());

        System.out.println("===== ALL DONE =====");
    }
}
