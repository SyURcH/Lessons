public class TriangleArea {
    public static double calculateArea(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) throw new IllegalArgumentException("Sides must be positive");
        if (a + b <= c || a + c <= b || b + c <= a) throw new IllegalArgumentException("Invalid triangle");

        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}