package tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * LireIdentite
 */
public class LireIdentite {
    public static void main(String[] args) {
        int i = 0;
        try {
            FileReader flot = new FileReader("tests/essai.txt") ;
            BufferedReader flotFiltre = new BufferedReader(flot) ;
            String ligne = flotFiltre.readLine() ;
            while (ligne != null) {
                ligne = flotFiltre.readLine() ;
                i++;
            }
            System.out.println(i);
            flotFiltre.close();
        } catch (IOException e) {}
    }
}