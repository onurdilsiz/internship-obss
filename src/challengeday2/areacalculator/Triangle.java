package challengeday2.areacalculator;

public class Triangle implements Shape{
    private  double floor;
    private double height;

    public double getFloor() {
        return floor;
    }

    public void setFloor(double floor) {
        this.floor = floor;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Triangle(double floor, double height) {
        this.floor = floor;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return (floor* height/2);
    }
}
