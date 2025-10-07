public class Main {
    public static void main(String[] args) {
        System.out.println("=== ЗАДАНИЕ 1: ЖИВОТНЫЕ ===");
        testAnimals();

        System.out.println("\n=== ЗАДАНИЕ 2: ГЕОМЕТРИЧЕСКИЕ ФИГУРЫ ===");
        testFigures();

        System.out.println("\n=== СТАТИСТИКА ===");
        System.out.println("Всего животных: " + Animal.getAnimalCount());
        System.out.println("Всего котов: " + Cat.getCatCount());
        System.out.println("Всего собак: " + Dog.getDogCount());
    }

    public static void testAnimals() {
        // Создаем животных
        Cat cat1 = new Cat("Барсик");
        Cat cat2 = new Cat("Мурзик");
        Cat cat3 = new Cat("Васька");
        Dog dog1 = new Dog("Бобик");
        Dog dog2 = new Dog("Шарик");

        // Тестируем бег и плавание
        cat1.run(150);
        cat1.run(250);
        cat1.swim(10);

        dog1.run(400);
        dog1.run(600);
        dog1.swim(5);
        dog1.swim(15);

        // Работа с миской
        Bowl bowl = new Bowl(20);
        System.out.println("\n--- Кормление котов ---");

        Cat[] cats = {cat1, cat2, cat3};

        // Первое кормление
        for (Cat cat : cats) {
            cat.eat(bowl, 10);
        }

        // Добавляем еду и кормим снова
        System.out.println("\n--- Добавляем еду и кормим снова ---");
        bowl.addFood(25);

        for (Cat cat : cats) {
            if (!cat.isFull()) {
                cat.eat(bowl, 10);
            }
        }

        // Проверяем сытость
        System.out.println("\n--- Состояние сытости котов ---");
        for (Cat cat : cats) {
            System.out.println(cat.getName() + " сыт: " + cat.isFull());
        }
    }

    public static void testFigures() {
        // Создаем фигуры
        Circle circle = new Circle(5, "Красный", "Черный");
        Rectangle rectangle = new Rectangle(4, 6, "Синий", "Белый");
        Triangle triangle = new Triangle(3, 4, 5, "Зеленый", "Желтый");

        // Выводим информацию о фигурах
        System.out.println("Круг:");
        circle.printInfo();

        System.out.println("\nПрямоугольник:");
        rectangle.printInfo();

        System.out.println("\nТреугольник:");
        triangle.printInfo();
    }
}