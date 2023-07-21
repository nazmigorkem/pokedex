package Shop;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Item> items = new ArrayList<>();
    public <T extends Item> void addItem(T item) {
        items.add(item);
    }

    public double calculateTotalPrice() {
        return items.stream().mapToDouble((x) -> x.price).sum();
    }

    public void displayCardContents() {
        items.forEach(x -> System.out.println(x.toString()));
    }

    public <T extends Item> T compareItems(T item1, T item2) {
        return Double.compare(item1.price, item2.price) == 1 ? item1 : item2;
    }

}
