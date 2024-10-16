package day4.library;

import java.util.ArrayList;
import java.util.List;

public class Shelf <T extends Book> {
    private List<T> items;

    public Shelf() {
        items = new ArrayList<>();
    }

    public void addItems(T obj) {
        items.add(obj);
    }

    public void displayCartContents() {
        System.out.println("Contents:");
        for (T item : items
        ) {
            System.out.println(item.toString());

        }
    }

    public static void main(String[] args) {
        Book app = new Book("app","author1",2000 );
        Book app2 = new Book("app2","author2",2009 );
        Fiction app3 = new Fiction("app3","author3",2019 );

        Shelf<Book> shelf1 = new Shelf<>();
        shelf1.addItems(app);
        shelf1.addItems(app2);
        shelf1.addItems(app3);
        shelf1.displayCartContents();

        Action action = new Action("action","author4",2019);
        Shelf<Action> actionShelf = new Shelf<Action>();
        actionShelf.addItems(action);

    }
}
