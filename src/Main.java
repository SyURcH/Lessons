import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Демонстрация работы со студентами
        System.out.println("=== ЗАДАНИЕ 1: Управление студентами ===");

        Set<Student> students = new HashSet<>(Arrays.asList(
                new Student("Иван Иванов", "Группа А", 1, Arrays.asList(4, 5, 3)),
                new Student("Петр Петров", "Группа Б", 2, Arrays.asList(2, 3, 2)),
                new Student("Мария Сидорова", "Группа А", 1, Arrays.asList(5, 5, 5)),
                new Student("Анна Козлова", "Группа В", 3, Arrays.asList(3, 4, 4))
        ));

        System.out.println("Исходный список студентов:");
        for (Student student : students) {
            System.out.println(student);
        }

        // Переводим успешных студентов
        StudentManager.promoteSuccessfulStudents(students);
        // Удаляем неуспевающих
        StudentManager.removeFailedStudents(students);

        System.out.println("\nПосле сессии:");
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println();
        StudentManager.printStudents(students, 2);

        // Демонстрация работы телефонного справочника
        System.out.println("\n=== ЗАДАНИЕ 2: Телефонный справочник ===");

        PhoneDirectory phoneDirectory = new PhoneDirectory();

        phoneDirectory.add("Иванов", "+7-911-123-45-67");
        phoneDirectory.add("Петров", "+7-912-345-67-89");
        phoneDirectory.add("Иванов", "+7-913-456-78-90");
        phoneDirectory.add("Сидорова", "+7-914-567-89-01");

        System.out.println("Телефоны Иванова: " + phoneDirectory.get("Иванов"));
        System.out.println("Телефоны Петрова: " + phoneDirectory.get("Петров"));
        System.out.println("Телефоны Сидоровой: " + phoneDirectory.get("Сидорова"));
        System.out.println("Телефоны несуществующего абонента: " + phoneDirectory.get("Козлов"));
    }
}