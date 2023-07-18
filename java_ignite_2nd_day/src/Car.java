public class Car {
    private String color;
    private int capacity;

    public Car() {
        color = "Blue";
        capacity = 5;
    }

    public Car(String color, int capacity) {
        this.color = color;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    public static void main(String[] args) {
        Car car = new Car();
        System.out.println(car);

        car = new Car("Red", 3);
        System.out.println(car);
    }
}
