public class Main {
    public static void main(String[] args) {
        // Демонстрация работы FactorialCalculator
        System.out.println("=== Factorial Calculator ===");
        System.out.println("Factorial of 5: " + FactorialCalculator.factorial(5));
        System.out.println("Factorial of 0: " + FactorialCalculator.factorial(0));

        // Демонстрация работы TriangleArea
        System.out.println("\n=== Triangle Area Calculator ===");
        System.out.println("Area of triangle (3,4,5): " + TriangleArea.calculateArea(3, 4, 5));

        // Демонстрация работы ArithmeticOperations
        System.out.println("\n=== Arithmetic Operations ===");
        System.out.println("10 + 5 = " + ArithmeticOperations.add(10, 5));
        System.out.println("10 - 5 = " + ArithmeticOperations.subtract(10, 5));
        System.out.println("10 * 5 = " + ArithmeticOperations.multiply(10, 5));
        System.out.println("10 / 5 = " + ArithmeticOperations.divide(10, 5));

        // Демонстрация работы NumberComparator
        System.out.println("\n=== Number Comparator ===");
        System.out.println("Compare 10 and 5: " + compareResultToString(NumberComparator.compare(10, 5)));
        System.out.println("Compare 5 and 5: " + compareResultToString(NumberComparator.compare(5, 5)));
        System.out.println("Compare 5 and 10: " + compareResultToString(NumberComparator.compare(5, 10)));
    }

    private static String compareResultToString(int result) {
        return result == 0 ? "equal" : (result > 0 ? "first is greater" : "second is greater");
    }
}