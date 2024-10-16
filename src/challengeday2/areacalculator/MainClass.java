package challengeday2.areacalculator;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Alan hesaplamak istediğiniz çokgeni seçin\n");
        System.out.println("1 - Üçgen\n" + "2 - Kare\n" + "3 - Dikdörtgen\n"+ "4 - Daire\n" );
        int decider = s.nextInt();
        if(decider==1){
            System.out.println("Taban alanı:");
            double floor = s.nextDouble();
            System.out.println("Yukseklik:");
            double height = s.nextDouble();
            Triangle triangle = new Triangle(floor,height);
            System.out.println("Sonuç "+floor+" * "+height +"/2 = " +triangle.calculateArea());


        } else if (decider==2) {
            System.out.println("Kenar uzunluğu");
            double floor = s.nextDouble();
            Square square = new Square(floor);
            System.out.println("Sonuç "+floor+" * "+floor + " = " +square.calculateArea());

        } else if (decider==3) {
            System.out.println("1. Kenar uzunluğu");
            double edge1 = s.nextDouble();
            System.out.println("2. Kenar uzunluğu");
            double edge2 = s.nextDouble();
            Rectangle r = new Rectangle(edge1,edge2);

            System.out.println("Sonuç "+edge1+" * "+edge2 + " = " +(r.calculateArea()));
        } else if (decider==4) {
            System.out.println("Yarıçap uzunluğu");
            Double radius = s.nextDouble();
            Circle c = new Circle(radius);
            System.out.println("Sonuç "+Math.PI+" * "+Math.pow(radius,2) + " = " +c.calculateArea());


        }
        else {
            System.out.println("Invalid input");
        }


        s.close();


    }
}
