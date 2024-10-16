package challengeday2.shoppingcart;

import java.util.ArrayList;

public class ShoppingCart {
    public static void main(String[] args) {
        Clothing c = new Clothing(0.1, 50);
        Electronics e = new Electronics(0.2,250);
        Grocery g = new Grocery(0.15,25);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(c);
        shoppingCart.addProduct(e);
        shoppingCart.addProduct(g);

        double total = shoppingCart.calculateTotalPrice();
        System.out.println(total);
    }

    private ArrayList<Product> products;

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double calculateTotalPrice() {
        double total = 0.0;
        for (Product product : products) {
            total += product.getPrice()*(1- product.getDiscountRate());
        }
        return total;
    }

}

