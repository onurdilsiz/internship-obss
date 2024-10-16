package challengeday2.areacalculator;

public class Square implements Shape{
    public Square(double edge) {
        this.edge = edge;
    }

    public double getEdge() {
        return edge;
    }

    public void setEdge(double edge) {
        this.edge = edge;
    }

    private double edge;


    @Override
    public double calculateArea() {
        return edge*edge;
    }
}
