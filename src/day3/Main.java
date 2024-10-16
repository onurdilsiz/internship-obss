package day3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a phrase: ");
        String string = scanner.nextLine();


        String result = string.replaceAll("\\s+", " ");

        String [] stringArr = result.split(" ");

        String current = "";
        for (String s : stringArr){
            if (s.length()>current.length()){
                current = s;
            }
        }
        String reversed = new StringBuilder(current).reverse().toString();
        System.out.println(reversed);





    }
}
