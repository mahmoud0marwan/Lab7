import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class JsonDatabaseManager {
    private final String usersFile;
    private final String coursesFile;
    private final Gson gson;

    public JsonDatabaseManager(String usersFilePath, String coursesFilePath) {
        this.usersFile = usersFilePath;
        this.coursesFile = coursesFilePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        createFileIfMissing(usersFile);
        createFileIfMissing(coursesFile);
    }

    private void createFileIfMissing(String filePath) {
        try {
            java.io.File file = new java.io.File(filePath);
            if (!file.exists()) {
                FileWriter writer = new FileWriter(file);
                writer.write("[]");
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("Error creating file: " + filePath);
        }
    }

    public List<User> loadUsers() {
        try {
            FileReader reader = new FileReader(usersFile);
            User[] arr = gson.fromJson(reader, User[].class);
            reader.close();
            List<User> list = new ArrayList<>();
            if (arr != null) {
                for (User u : arr) {
                    list.add(u);
                }
            }
            return list;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void saveUsers(List<User> users) {
        try {
            FileWriter writer = new FileWriter(usersFile);
            gson.toJson(users, writer);
            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving users.");
        }
    }

    public boolean isUserIdUnique(String userId) {
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.getUserId().equals(userId)) {
                return false;
            }
        }
        return true;
    }

    public List<Course> loadCourses() {
        try {
            FileReader reader = new FileReader(coursesFile);
            Course[] arr = gson.fromJson(reader, Course[].class);
            reader.close();
            List<Course> list = new ArrayList<>();
            if (arr != null) {
                for (Course c : arr) {
                    list.add(c);
                }
            }
            return list;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void saveCourses(List<Course> courses) {
        try {
            FileWriter writer = new FileWriter(coursesFile);
            gson.toJson(courses, writer);
            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving courses.");
        }
    }

    public boolean isCourseIdUnique(String courseId) {
        List<Course> courses = loadCourses();
        for (Course c : courses) {
            if (c.getCourseId().equals(courseId)) {
                return false;
            }
        }
        return true;
    }

    public User getUserById(String userId) {
        List<User> users = loadUsers();

        for (User u : users) {
            if (u.getUserId().equals(userId)) {
                return u;
            }
        }
        return null;
    }
    public Course getCourseById(String courseId) {
        List<Course> courses = loadCourses();

        for (Course c : courses) {
            if (c.getCourseId().equals(courseId)) {
                return c;
            }
        }
        return null;
    }

}
public User getUserById(String userId) {
    List<User> users = loadUsers();

    for (User u : users) {
        if (u.getUserId().equals(userId)) {
            return u;
        }
    }
    return null;
}

