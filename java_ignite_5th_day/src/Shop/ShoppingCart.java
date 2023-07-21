package Shop;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart <T extends Item> {
    private final List<T> items = new ArrayList<>();
    public void addItem(T item) {
        items.add(item);
    }

    public double calculateTotalPrice() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }

    public void displayCardContents() {
        items.forEach(x -> System.out.println(x.toString()));
    }

    public T compareItems(T item1, T item2) {
        return Double.compare(item1.getPrice(), item2.getPrice()) == 1 ? item1 : item2;
    }

}
