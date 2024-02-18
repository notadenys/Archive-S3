package testAwaz;

import awaz.Awaz;
import java.util.Scanner;

/**
 * Cette classe est le menu pour faire la melodie
 * @author Denys
 * @version Septembre 2023
 */
public class Menu {
    public static void main(String[] args) {
        // Création d'une mélodie vide
        Awaz melodie = new Awaz() ;
        Scanner myObj = new Scanner(System.in);
        int choix = 0;

        while(choix != -1)
        {
        System.out.println("Menu");
        System.out.println("1. Ajouter une note");
        System.out.println("2. Enregistrer la mélodie dans un fichier");
        System.out.println("3. Afficher vrai si la mélodie ne contient aucune note, faux sinon");
        System.out.println("4. Afficher la dernière note de la mélodie");
        System.out.println("5. Afficher le titre de la mélodie");
        System.out.println("6. Transposer chaque note de la mélodie");
        System.out.println("7. Afficher le nombre de notes de la mélodie");
        System.out.println("8. Effacer toutes les notes");
        System.out.println("9. Construire la mélodie à partir d'un fichier");
        System.out.println("10. Fixer le titre de la mélodie");
        System.out.println("11. Afficher la mélodie au format ABC");
        System.out.println("12. Afficher toutes les notes de la mélodie");
        System.out.println("Type -1 for exit.");
        
        
        
	System.out.print("Votre choix: ");
	choix = myObj.nextInt();
	myObj.nextLine();
	
	switch (choix) {
		case 1:
			System.out.print("Entrez votre note: ");
			melodie.add(myObj.nextLine());
			System.out.println("Done!");
			break;
		case 2:
			System.out.print("Nom de fichier: ");
			melodie.enregistrer(myObj.nextLine());
			System.out.println("Done!");
			break;
		case 3:
			System.out.println(melodie.estVide());
			break;
		case 4:
			System.out.println(melodie.getLast());
			break;
		case 5:
			System.out.println(melodie.getTitre());
			break;
		case 6:
			System.out.print("Pour combien de demi-octaves vous voulez transposer votre melodie: ");
			melodie.transposer(myObj.nextInt());
			myObj.nextLine();
			System.out.println("Done!");
			break;
		case 7:
			System.out.println(melodie.nbNotes());
			break;
		case 8:
			melodie.nouveau();
			System.out.println("Done!");
			break;
		case 9:
			System.out.print("Nom de fichier: ");
			melodie.ouvrir(myObj.nextLine());
			System.out.println("Done!");
			break;
		case 10:
			System.out.print("Entrez le titre de la melodie: ");
			melodie.setTitre(myObj.nextLine());
			System.out.println("Done!");
			
			break;
		case 11:
			System.out.println(melodie.toABC());
			break;
		case 12:
			System.out.print(melodie.toString());
			break;
		}
	System.out.println("");
        }
    }
}
