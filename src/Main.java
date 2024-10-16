import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int randomNumber =(int) (Math.random() *100);
        int count = 5 ;
        while(count>0){
            System.out.println("Enter a number from 0 to 100");
            int guess = scanner.nextInt();
            count--;

            if (guess == randomNumber){
                System.out.println("Congrats");
                break;
            } else if (guess > randomNumber&& count>0 ) {
                System.out.println("Try smaller");
            }else if (guess < randomNumber&& count>0 ){
                System.out.println("Try bigger");
            }else{
                System.out.println("You could not find it. The number is "+ randomNumber);
            }
        }
        scanner.close();


    }
}