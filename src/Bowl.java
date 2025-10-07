public class Bowl {
    private int foodAmount;

    public Bowl(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public boolean decreaseFood(int amount) {
        if (amount <= foodAmount) {
            foodAmount -= amount;
            System.out.println("Еда в миске уменьшилась на " + amount + ". Осталось: " + foodAmount);
            return true;
        } else {
            System.out.println("Недостаточно еды в миске! Требуется: " + amount + ", доступно: " + foodAmount);
            return false;
        }
    }

    public void addFood(int amount) {
        foodAmount += amount;
        System.out.println("В миску добавлено " + amount + " еды. Теперь в миске: " + foodAmount);
    }

    public int getFoodAmount() {
        return foodAmount;
    }
}