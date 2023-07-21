package Shop;

public class Main {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        Water water = new Water(2.435, "Erikli");
        Water water2 = new Water(2.8, "Sirma");

        shoppingCart.addItem(water);
        shoppingCart.addItem(water2);
        shoppingCart.displayCardContents();
        System.out.println(shoppingCart.calculateTotalPrice());
    }
}
