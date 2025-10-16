import java.util.Set;

public class StudentManager {
    public static void removeFailedStudents(Set<Student> students) {
        students.removeIf(student -> student.getAverageGrade() < 3.0);
    }

    public static void promoteSuccessfulStudents(Set<Student> students) {
        for (Student student : students) {
            if (student.getAverageGrade() >= 3.0) {
                student.promote();
            }
        }
    }

    public static void printStudents(Set<Student> students, int course) {
        System.out.println("Студенты " + course + "-го курса:");
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println("  - " + student.getName());
            }
        }
    }
}