package Lab;

public class ThePen {

    public ThePen() {
    }

    public void drawRectangle(Rectangle r){
        System.out.println(  r.getHeight()*r.getWidth());
    }

    public void drawCircle(Circle c){
        System.out.println(  Math.PI * c.getRadius()* c.getRadius());
    }
    public void drawShape(Shape  s){
        if (s instanceof Circle){
            Circle c = (Circle) s;
        System.out.println(  Math.PI * c.getRadius()* c.getRadius());
        } else if (s instanceof Rectangle) {
            Rectangle r = (Rectangle) s;

            System.out.println(  r.getHeight()*r.getWidth());
        }
    }
    public void changeColorRectangle(String color, Rectangle r){
        r.setColor(color);
        System.out.println("Color changed to "+ color);
    }
    public void changeColorCircle(String color, Circle c){
        c.setColor(color);
        System.out.println("Color changed to "+ color);

    }

    public static void main(String[] args) {

        ThePen p = new ThePen();
        Rectangle r = new Rectangle(5,10);
        Circle c = new Circle(5);

        p.drawRectangle(r);
        p.drawCircle(c);
        p.drawShape(r);
        p.drawShape(c);

        p.changeColorRectangle("blue",r);

        p.changeColorCircle("white",c);

    }

}
