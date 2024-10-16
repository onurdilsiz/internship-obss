package challengeday2.shoppingcart;

public class Product {
    private double price;
    public Product(double discountRate, double price) {
        this.discountRate = discountRate;
        this.price = price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    private double discountRate;
}
