package Shop;

public class Main {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        Water water = new Water(2.435, "Erikli");

        shoppingCart.addItem(water);
        shoppingCart.displayCardContents();
    }
}
