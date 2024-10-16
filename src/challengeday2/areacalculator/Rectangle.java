package challengeday2.areacalculator;

public class Rectangle implements Shape{

   private double edge1;

    public Rectangle(double edge1, double edge2) {
        this.edge1 = edge1;
        this.edge2 = edge2;
    }

    public double getEdge1() {
        return edge1;
    }

    public void setEdge1(double edge1) {
        this.edge1 = edge1;
    }

    public double getEdge2() {
        return edge2;
    }

    public void setEdge2(double edge2) {
        this.edge2 = edge2;
    }

    private  double edge2;


    @Override
    public double calculateArea() {
        return (edge1* edge2);
    }
}
