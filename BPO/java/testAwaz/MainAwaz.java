package testAwaz;

import awaz.Awaz;
import awaz.AwazImage;
import java.util.Scanner;

/**
 * Cette classe est le point de départ du Tp Awaz
 * @author martine
 * @version Janvier 2018
 */
public class MainAwaz {
    public static void main(String[] args) {
        // Création d'une mélodie vide
        Awaz melodie = new Awaz();

        melodie.add("Do");
        melodie.add("Fa");

	System.out.print(melodie.toString());
	System.out.println(melodie.nbNotes());
	
	melodie.transposer(2);
	
	System.out.println(melodie.toString());
	
	melodie.nouveau();
	melodie.add("Do");
	melodie.add("Ré");
	melodie.add("Mi");
	melodie.add("Mi");
	melodie.add("Mi");
	melodie.add("La");
	melodie.add("La");
	melodie.add("Do");
	melodie.add("Do");
	melodie.add("Si");
	melodie.add("La");
	melodie.add("Sol");
	melodie.add("La");
	melodie.add("Si");
	
	for (int i = 0; i < 14; i++)
	{
	System.out.print(melodie.ieme(i));
	System.out.print(" ");
	}
	System.out.println();
	
	
	Scanner myObj = new Scanner(System.in);
	System.out.println("Nom de fichier:");
	String fileName = myObj.nextLine();
	melodie.enregistrer(fileName);
	
	System.out.println(melodie.toABC());
	
	
	AwazImage image = new AwazImage();
	image.setMelodie(melodie.toABC());
	image.setTitre(fileName);
    }
}
