package tests;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Ecrire
 */
public class Lire {

    public static void main(String[] args) {
        try {
            FileReader flot = new FileReader("tests/" + args[0]) ;
            Scanner sc = new Scanner(flot);
            int counter = 0;
            int sum = 0;
            while(sc.hasNextInt()){
                counter++;
                sum += sc.nextInt();
            }
            System.out.println("Counter: " + counter);
            System.out.println("Sum: " + sum);
            System.out.println("Avg: " + sum/counter);
            sc.close();
        } catch (IOException e) {}
    }
}