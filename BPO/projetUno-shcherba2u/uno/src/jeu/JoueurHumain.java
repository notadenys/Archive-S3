package src.jeu;

import src.erreurs.InvalidCoupExtension;
import src.cartes.Carte;

import java.util.Objects;
import java.util.Scanner;

public class JoueurHumain extends Joueur{
    public JoueurHumain(Uno uno, String nom)
    {
        super(uno, nom);
    }

    public void jouer() throws InvalidCoupExtension
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(getMain().getAffichage());

        boolean successfulCoup = false;
        while (!successfulCoup)
        {
            System.out.println("\nMenu :\n" +
                    "Tapez 'j' pout jouer la carte.\n" +
                    "Tapez 'p' pour piocher.\n" +
                    "Votre choix?");
            String choice = scanner.nextLine();
            // piocher
            if (Objects.equals(choice, "p"))
            {
                piocherCarte();
                successfulCoup = true;
            }
            // jouer la carte
            else if (Objects.equals(choice, "j"))
            {
                jouerCarte();
                successfulCoup = true;
            }
            else
            {
                throw new InvalidCoupExtension("Invalid choice.");
            }
        }
    }


    private void piocherCarte()
    {
        piocher();
        System.out.println("Vouz avez pris " + getMain().getSommet().getAffichage());

        // if drawn card can be placed on top
        if(getUno().getTopCarte().peutEtreRecouvertePar(getMain().getSommet()))
        {
            // picking a color for the card if needed
            if(getMain().getSommet().estSansCouleur())
            {
                try
                {
                    System.out.println("Choisir le couleur :\n" +
                            "'v' pour Vert\n" +
                            "'r' pour Rouge\n" +
                            "'b' pour Bleu\n" +
                            "'j' pour Jaune");
                    char col = new Scanner(System.in).next(".").charAt(0);
                    getMain().getSommet().choisirCouleur(col);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            getUno().getTalon().ajouter(getMain().piocher());
            System.out.println("Vouz avez joue " + getUno().getTopCarte().getAffichage());
            getUno().getTopCarte().appliquerEffet();
        } else {
            System.out.println("Vouz n'avez pas la carte a jouer");
        }
    }

    private void jouerCarte()
    {
        Scanner scanner = new Scanner(System.in);
        boolean coupFait = false;

        for (Carte c : getMain())
        {
                /* checking if at least one card can be placed
                   if so letting a person choose a card to play and then breaking a loop */
            if (getUno().getTopCarte().peutEtreRecouvertePar(c))
            {
                do
                {
                    System.out.print("Choisir le numero de la carte : ");
                    int index = scanner.nextInt();
                    Carte carteChoisie = getMain().getCarte(index-1);

                    if(getUno().getTopCarte().peutEtreRecouvertePar(carteChoisie))
                    {
                        // picking a color for the card if needed
                        if(carteChoisie.estSansCouleur())
                        {
                            try
                            {
                                System.out.println("Choisir le couleur :\n" +
                                        "'v' pour Vert\n" +
                                        "'r' pour Rouge\n" +
                                        "'b' pour Bleu\n" +
                                        "'j' pour Jaune");

                                char col = scanner.next(".").charAt(0);
                                getMain().getSommet().choisirCouleur(col);
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        }

                        getUno().getTalon().ajouter(carteChoisie);
                        getMain().enlever(carteChoisie);
                        System.out.println("Vouz avez joue " + carteChoisie.getAffichage());
                        carteChoisie.appliquerEffet();

                        coupFait = true;
                    }
                    else
                    {
                        System.out.println("Invalid choice");
                    }
                } while (!coupFait);
                break;
            }
        }
        if (!coupFait)
        {
            System.out.println("Vouz n'avez aucune carte a jouer. Il faut piocher la carte.");
            piocherCarte();
        }
    }
}
