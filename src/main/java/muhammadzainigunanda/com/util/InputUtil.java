package muhammadzainigunanda.com.util;

import java.util.Scanner;

public class InputUtil {
     
     private static Scanner scanner = new Scanner(System.in);

     public static String input(String input) {
          System.out.println(input + " :");

          String dataInput = scanner.nextLine();
          return dataInput;
     }

}
