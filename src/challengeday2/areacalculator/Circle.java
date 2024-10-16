package challengeday2.areacalculator;

public class Circle implements Shape{
    public Circle(double radius) {
        this.radius = radius;

    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    private double radius;


    @Override
    public double calculateArea() {
        return (Math.PI*Math.pow(radius,2));
    }
}
