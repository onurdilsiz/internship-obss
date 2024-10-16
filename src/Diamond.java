import java.util.Scanner;

public class Diamond {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a size: ");
        int size = scanner.nextInt();

        // Ensure the size is odd
        if (size % 2 == 0) {
            System.out.println("Size must be an odd number.");
        } else {
            int n = size / 2; // Determines the number of rows in the upper half

            // Print the upper half of the diamond
            for (int i = 0; i <= n; i++) {
                for (int j = n; j > i; j--) {
                    System.out.print(" ");
                }
                for (int k = 0; k < (2 * i + 1); k++) {
                    System.out.print("*");
                }
                System.out.println();
            }

            // Print the lower half of the diamond
            for (int i = n - 1; i >= 0; i--) {
                for (int j = n; j > i; j--) {
                    System.out.print(" ");
                }
                for (int k = 0; k < (2 * i + 1); k++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        }

        scanner.close();
    }
}
