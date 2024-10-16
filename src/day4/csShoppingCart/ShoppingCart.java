package day4.csShoppingCart;
import day4.function.labStreams.Person;

import java.util.*;

public class ShoppingCart <T>{
    private List<T> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }
    public void addItems(T obj){
        items.add(obj);
    }
    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (T item : items) {
            if (item instanceof Product) {
                Product product = (Product) item;
                totalPrice += product.getPrice() * product.getQuantity();
            }
        }
        return totalPrice;
    }
    public void displayCartContents(){
        for (T item :items
             ) {
            System.out.println(item.toString());

        }
    }
    public T compareItems(T item1, T item2){
        if (item1 instanceof Product && item2 instanceof Product) {
            Product product1 = (Product) item1;
            Product product2 = (Product) item2;
            return (T) (product1.getPrice() < product2.getPrice() ? product1 : product2);
        }else{return  null;
        }


    }

    public static void main(String[] args) {
        Product app = new Product("app",23,3 );
        Product app2 = new Product("app2",150,1 );
        Product app3 = new Product("app3",50,2 );
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItems(app);
        shoppingCart.addItems(app2);
        shoppingCart.addItems(app3);

        shoppingCart.displayCartContents();
        System.out.println(shoppingCart.calculateTotalPrice());
        Product app4 = new Product("app4",23,3 );
        Product app5 = new Product("app5",150,1 );
        System.out.println(shoppingCart.compareItems(app4,app5));




    }

}
