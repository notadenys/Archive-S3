package src.jeu;

import src.erreurs.InvalidCoupExtension;
import src.cartes.Carte;
import src.cartes.FabriqueCartes;
import src.cartes.PaquetDeCartes;

public abstract class Joueur {
    private String nom;
    private int totalScore;
    private PaquetDeCartes main;
    private Uno uno;

    public Joueur(Uno uno, String nom)
    {
        this.nom = nom;
        this.uno = uno;

        FabriqueCartes fc = FabriqueCartes.getInstance();
        main = fc.getPaquetVide();
    }

    public void updateTotalScore(){totalScore += main.getValeur();}

    /**
     * gets a card from pioche and adds it to player's hand (returns talon if pioche is empty)
     */
    public void piocher()
    {
        if (uno.getPioche().estVide())
        {
            Carte top = uno.getTopCarte();
            uno.getTalon().retourner();
            uno.getTalon().mettreSurTop(top);

            System.out.println("Le talon a ete retourne");
        }
        main.ajouter(uno.getPioche().piocher());
    }

    public PaquetDeCartes getMain() {return main;}
    public String getNom() {return nom;}
    public int getTotalScore() {return totalScore;}
    public Uno getUno() {return uno;}

    public boolean aMainVide() {return main.estVide();}

    public void afficherTotalScore()
    {
        System.out.println(nom + " : " + totalScore);
    }

    abstract void jouer() throws InvalidCoupExtension;
}
