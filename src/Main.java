import java.util.*;

// Все классы в одном файле
class Student {
    private String name;
    private String group;
    private int course;
    private List<Integer> grades;

    public Student(String name, String group, int course, List<Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = grades;
    }

    public double getAverageGrade() {
        if (grades == null || grades.isEmpty()) return 0;
        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public void promote() {
        this.course++;
    }

    public String getName() { return name; }
    public int getCourse() { return course; }

    @Override
    public String toString() {
        return String.format("Студент: %s, Группа: %s, Курс: %d, Средний балл: %.2f",
                name, group, course, getAverageGrade());
    }
}

class StudentManager {
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

class PhoneDirectory {
    private final Map<String, List<String>> directory = new HashMap<>();

    public void add(String surname, String phoneNumber) {
        if (!directory.containsKey(surname)) {
            directory.put(surname, new ArrayList<>());
        }
        directory.get(surname).add(phoneNumber);
    }

    public List<String> get(String surname) {
        return directory.getOrDefault(surname, new ArrayList<>());
    }
}

public class Main {
    public static void main(String[] args) {
        // Тестируем студентов
        Set<Student> students = new HashSet<>();
        students.add(new Student("Иван Иванов", "Группа А", 1, Arrays.asList(4, 5, 3)));
        students.add(new Student("Петр Петров", "Группа Б", 2, Arrays.asList(2, 3, 2)));
        students.add(new Student("Мария Сидорова", "Группа А", 1, Arrays.asList(5, 5, 5)));

        System.out.println("До обработки:");
        for (Student s : students) {
            System.out.println(s);
        }

        StudentManager.promoteSuccessfulStudents(students);
        StudentManager.removeFailedStudents(students);

        System.out.println("\nПосле обработки:");
        for (Student s : students) {
            System.out.println(s);
        }

        // Тестируем телефонный справочник
        PhoneDirectory pd = new PhoneDirectory();
        pd.add("Иванов", "123");
        pd.add("Иванов", "456");
        pd.add("Петров", "789");

        System.out.println("\nТелефоны Иванова: " + pd.get("Иванов"));
    }
}