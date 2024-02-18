package tests;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * EcrireIdentite
 */
public class EcrireIdentite {
    public static void main(String[] args) {
        BufferedWriter flotFiltre ; FileWriter flot ;
        try {
            flot = new FileWriter("tests/essai.txt") ;
            flotFiltre = new BufferedWriter(flot) ;
            flotFiltre.write("SHCHERBA") ;
            flotFiltre.newLine() ;
            flotFiltre.write("Denys") ;
            flotFiltre.newLine() ;
            flotFiltre.write("*adresse*") ;
            flotFiltre.close() ;
        } catch (IOException e) {}
    }
}