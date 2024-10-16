import java.util.Arrays;
import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int [] numbers = new int [number] ;
        int index = 0 ;
        while(number>0 ){
            int no = scanner.nextInt();
            numbers[index]= no;
            number--;
            index++;
        }
        Arrays.sort(numbers);
        for (int i :numbers){
            System.out.println(i);
        }


    }
}
