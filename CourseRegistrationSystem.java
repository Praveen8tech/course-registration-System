import java.util.Scanner;

class Course {
    private int courseId;
    private String courseName;

    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void displayCourse() {
        System.out.println(courseId + " - " + courseName);
    }
}

class Student {

    private int studentId;
    private String studentName;

    private Course[] registeredCourses = new Course[5];
    private int count = 0;

    public Student(int studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public void registerCourse(Course course) {

        for (int i = 0; i < count; i++) {
            if (registeredCourses[i].getCourseId() == course.getCourseId()) {
                System.out.println("Course already registered.");
                return;
            }
        }

        if (count < registeredCourses.length) {
            registeredCourses[count] = course;
            count++;
            System.out.println("Course Registered Successfully.");
        } else {
            System.out.println("Maximum course limit reached.");
        }
    }

    public void viewRegisteredCourses() {

        if (count == 0) {
            System.out.println("No Courses Registered.");
            return;
        }

        System.out.println("\nRegistered Courses:");

        for (int i = 0; i < count; i++) {
            registeredCourses[i].displayCourse();
        }
    }

    public void dropCourse(int id) {

        int index = -1;

        for (int i = 0; i < count; i++) {
            if (registeredCourses[i].getCourseId() == id) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Course not found.");
            return;
        }

        for (int i = index; i < count - 1; i++) {
            registeredCourses[i] = registeredCourses[i + 1];
        }

        registeredCourses[count - 1] = null;
        count--;

        System.out.println("Course Dropped Successfully.");
    }
}

//------------------ Registration System ------------------
class RegistrationSystem {

    private Course[] courses = new Course[5];

    public RegistrationSystem() {

        courses[0] = new Course(101, "Java");
        courses[1] = new Course(102, "Python");
        courses[2] = new Course(103, "Database");
        courses[3] = new Course(104, "Operating System");
        courses[4] = new Course(105, "Computer Networks");
    }

    public void showCourses() {

        System.out.println("\nAvailable Courses");

        for (int i = 0; i < courses.length; i++) {
            courses[i].displayCourse();
        }
    }

    public Course searchCourse(int id) {

        for (int i = 0; i < courses.length; i++) {

            if (courses[i].getCourseId() == id) {
                return courses[i];
            }
        }

        return null;
    }
}

//------------------ Main Class ------------------
public class CourseRegistrationSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=====----|| COURSE REGISTRATION SYSTEM ||----=====");

        System.out.print("Enter Student ID : ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Student Name : ");
        String name = sc.nextLine();

        Student student = new Student(id, name);
        RegistrationSystem system = new RegistrationSystem();

        int choice;

        do {

            System.out.println("\n========== MENU ==========");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register Course");
            System.out.println("3. View Registered Courses");
            System.out.println("4. Drop Course");
            System.out.println("5. Exit");
            System.out.print("Enter Choice : ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    system.showCourses();
                    break;

                case 2:
                    system.showCourses();
                    System.out.print("Enter Course ID : ");
                    int courseId = sc.nextInt();

                    Course course = system.searchCourse(courseId);

                    if (course != null)
                        student.registerCourse(course);
                    else
                        System.out.println("Invalid Course ID");

                    break;

                case 3:
                    student.viewRegisteredCourses();
                    break;

                case 4:
                    student.viewRegisteredCourses();
                    System.out.print("Enter Course ID to Drop : ");
                    int dropId = sc.nextInt();
                    student.dropCourse(dropId);
                    break;

                case 5:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }

        } while (choice != 5);
    }
}