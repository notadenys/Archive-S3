package src.cartes;

import src.jeu.Uno;

public class PasseTonTour extends CarteAction {
    public PasseTonTour(Uno uno, Couleur couleur)
    {
        super(uno, couleur);
    }

    public String getNom() {
        return "PasseTonTour";
    }

    public void appliquerEffet() {
        getUno().prochainJouer();
        System.out.println(getUno().getNomDeJoueurCourant() + " passes son tour");
    }
}
