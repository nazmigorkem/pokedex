package Shop;

public abstract class Item {
    double price;
    String name;

    public Item(double price, String name) {
        this.price = price;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%.2f is the price for %s item", price, name);
    }
}
