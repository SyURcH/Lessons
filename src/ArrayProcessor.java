public class ArrayProcessor {

    public static int processArray(String[][] matrix) throws MyArraySizeException, MyArrayDataException {
        if (matrix.length != 4) {
            throw new MyArraySizeException("Массив должен иметь 4 строки");
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length != 4) {
                throw new MyArraySizeException("Строка " + i + " должна содержать 4 элемента");
            }
        }

        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                try {
                    sum += Integer.parseInt(matrix[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(
                            "Неверные данные в ячейке [" + i + "][" + j + "]: '" + matrix[i][j] + "'"
                    );
                }
            }
        }
        return sum;
    }

    public static void demonstrateIndexException() {
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано ArrayIndexOutOfBoundsException: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String[][] validMatrix = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] invalidDataMatrix = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "abc", "16"}
        };

        String[][] invalidSizeMatrix = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };

        System.out.println("=== Тест 1: Корректный массив ===");
        try {
            int result = processArray(validMatrix);
            System.out.println("Сумма элементов: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Тест 2: Массив с неверными данными ===");
        try {
            int result = processArray(invalidDataMatrix);
            System.out.println("Сумма элементов: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Тест 3: Массив неверного размера ===");
        try {
            int result = processArray(invalidSizeMatrix);
            System.out.println("Сумма элементов: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Тест 4: ArrayIndexOutOfBoundsException ===");
        demonstrateIndexException();
    }
}