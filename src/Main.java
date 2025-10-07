public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1-2 ===");

        Product[] productsArray = new Product[5];

        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025",
                "Samsung Corp.", "Korea", 5599.0, true);

        productsArray[1] = new Product("iPhone 16", "15.01.2025",
                "Apple Inc.", "USA", 4999.0, false);

        productsArray[2] = new Product("Xiaomi Mi 14", "10.12.2024",
                "Xiaomi Corporation", "China", 2999.0, true);

        productsArray[3] = new Product("MacBook Air M3", "20.11.2024",
                "Apple Inc.", "USA", 4599.0, false);

        productsArray[4] = new Product("Sony WH-1000XM5", "05.03.2024",
                "Sony Corporation", "Japan", 899.0, true);

        System.out.println("Информация о всех товарах:");
        System.out.println("==========================");
        for (int i = 0; i < productsArray.length; i++) {
            System.out.println("Товар " + (i + 1) + ":");
            productsArray[i].printInfo();
        }

        System.out.println("=== 3 ===");

        Park centralPark = new Park("Центральный парк");

        centralPark.addAttraction("Американские горки", "10:00-22:00", 500.0);
        centralPark.addAttraction("Колесо обозрения", "09:00-23:00", 300.0);
        centralPark.addAttraction("Карусель", "10:00-20:00", 200.0);
        centralPark.addAttraction("Комната страха", "12:00-24:00", 400.0);

        centralPark.printAllAttractions();

        System.out.println("=== Внутренний класс ===");
        Park.Attraction newAttraction = centralPark.new Attraction("Водные горки", "11:00-19:00", 600.0);
        newAttraction.printAttractionInfo();
    }
}