public class Park {
    private String parkName;
    private Attraction[] attractions;

    public Park(String parkName) {
        this.parkName = parkName;
        this.attractions = new Attraction[0];
    }

    public class Attraction {
        private String attractionName;
        private String workingHours;
        private double price;

        public Attraction(String attractionName, String workingHours, double price) {
            this.attractionName = attractionName;
            this.workingHours = workingHours;
            this.price = price;
        }

        public void printAttractionInfo() {
            System.out.println("Аттракцион: " + attractionName);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + price + " руб.");
            System.out.println("Парк: " + parkName);
        }

        public String getAttractionName() {
            return attractionName;
        }

        public String getWorkingHours() {
            return workingHours;
        }

        public double getPrice() {
            return price;
        }
    }

    public void addAttraction(String name, String hours, double price) {
        Attraction newAttraction = new Attraction(name, hours, price);

        Attraction[] newArray = new Attraction[attractions.length + 1];

        for (int i = 0; i < attractions.length; i++) {
            newArray[i] = attractions[i];
        }

        newArray[attractions.length] = newAttraction;
        attractions = newArray;
    }

    public void printAllAttractions() {
        System.out.println("Парк: " + parkName);
        System.out.println("Список аттракционов:");
        System.out.println("====================");
        for (Attraction attraction : attractions) {
            attraction.printAttractionInfo();
            System.out.println("--------------------");
        }
    }

    public String getParkName() {
        return parkName;
    }

    public Attraction[] getAttractions() {
        return attractions;
    }
}