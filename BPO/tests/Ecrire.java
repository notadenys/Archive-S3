package tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Ecrire
 */
public class Ecrire {

    public static void main(String[] args) {
        BufferedWriter flotFiltre ; FileWriter flot ;
        try {
            if (new File("tests/"+args[0]).isFile())
            {
                throw new IOException("This file already exists!");
            }
            flot = new FileWriter("tests/"+args[0]) ;
            flotFiltre = new BufferedWriter(flot) ;
            for(int i = 1; i <= 1000; i++)
            {
                flotFiltre.write(String.valueOf(i)) ;
                flotFiltre.newLine() ;
            }
            flotFiltre.close() ;
        } catch (IOException e) {System.out.println(e.getMessage());}
    }
}