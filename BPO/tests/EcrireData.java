package tests;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class EcrireData {
    public static void main(String[] args) {
        BufferedWriter flotFiltre ; FileWriter flot ;
        try {
            flot = new FileWriter("tests/" + args[0]) ;
            flotFiltre = new BufferedWriter(flot) ;
            int nbLignes = getRandomNumberInRange(10, 30);
            int character = getRandomNumberInRange(97, 122);
            for(int i = 0; i < nbLignes - 1; i++)
            {
                flotFiltre.write((char)character + " " + getRandomNumberInRange(-5, 7));
                flotFiltre.newLine();
                character = (character < 122) ? character + 1 : 97;
            }
            flotFiltre.write((char)character + " " + getRandomNumberInRange(-5, 7));
            flotFiltre.newLine();
            flotFiltre.close() ;
        } catch (IOException e) {}
    }

    private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
