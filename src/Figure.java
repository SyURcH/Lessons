public interface Figure {
    String getFillColor();
    String getBorderColor();
    double calculateArea();

    default double calculatePerimeter() {
        return 0;
    }

    default void printInfo() {
        System.out.println("Периметр: " + calculatePerimeter() +
                ", Площадь: " + calculateArea() +
                ", Цвет фона: " + getFillColor() +
                ", Цвет границ: " + getBorderColor());
    }
}