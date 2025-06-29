import java.util.*;

class Course {
    String code, title, description, schedule;
    int capacity;
    List<String> enrolledStudents;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = new ArrayList<>();
    }

    public boolean hasAvailableSlot() {
        return enrolledStudents.size() < capacity;
    }

    public void displayDetails() {
        System.out.println("Code: " + code);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Schedule: " + schedule);
        System.out.println("Capacity: " + capacity + " | Enrolled: " + enrolledStudents.size());
        System.out.println("Available Slots: " + (capacity - enrolledStudents.size()));
        System.out.println("---------------------------");
    }
}

class Student {
    String studentId, name;
    List<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            System.out.println("❗ Already registered in " + course.code);
        } else if (!course.hasAvailableSlot()) {
            System.out.println("❌ Course " + course.code + " is full.");
        } else {
            course.enrolledStudents.add(this.studentId);
            registeredCourses.add(course);
            System.out.println("✅ Successfully registered in " + course.code);
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.enrolledStudents.remove(this.studentId);
            System.out.println("✅ Dropped course " + course.code);
        } else {
            System.out.println("❌ You are not registered in " + course.code);
        }
    }

    public void listRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("📭 No courses registered.");
            return;
        }
        System.out.println("📘 Registered Courses:");
        for (Course c : registeredCourses) {
            System.out.println("- " + c.code + " | " + c.title);
        }
    }
}

public class StudentCourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample course list
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CSE101", "Intro to CS", "Basics of CS", 3, "Mon 10-12"));
        courses.add(new Course("MAT201", "Calculus", "Differential and Integral", 2, "Tue 2-4"));
        courses.add(new Course("PHY150", "Physics", "Mechanics & Thermodynamics", 2, "Wed 9-11"));

        // Create a student
        System.out.println("🎓 Welcome to Course Registration System");
        System.out.print("Enter Student ID: ");
        String studentId = sc.nextLine();
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        Student student = new Student(studentId, name);

        int choice;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. 📋 View Available Courses");
            System.out.println("2. ➕ Register for a Course");
            System.out.println("3. ➖ Drop a Course");
            System.out.println("4. 📘 View Registered Courses");
            System.out.println("5. ❌ Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("\n📋 Available Courses:");
                    for (Course c : courses) {
                        c.displayDetails();
                    }
                    break;

                case 2:
                    System.out.print("Enter course code to register: ");
                    String regCode = sc.nextLine();
                    Course regCourse = findCourseByCode(courses, regCode);
                    if (regCourse != null) {
                        student.registerCourse(regCourse);
                    } else {
                        System.out.println("❌ Course not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter course code to drop: ");
                    String dropCode = sc.nextLine();
                    Course dropCourse = findCourseByCode(courses, dropCode);
                    if (dropCourse != null) {
                        student.dropCourse(dropCourse);
                    } else {
                        System.out.println("❌ Course not found.");
                    }
                    break;

                case 4:
                    student.listRegisteredCourses();
                    break;

                case 5:
                    System.out.println("👋 Thank you. Goodbye!");
                    break;

                default:
                    System.out.println("❗ Invalid option.");
            }

        } while (choice != 5);

        sc.close();
    }

    public static Course findCourseByCode(List<Course> courses, String code) {
        for (Course c : courses) {
            if (c.code.equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }
}
