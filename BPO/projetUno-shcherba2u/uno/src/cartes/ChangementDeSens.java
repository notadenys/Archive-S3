package src.cartes;

import src.jeu.Uno;

public class ChangementDeSens extends CarteAction {
    public ChangementDeSens(Uno uno, Couleur couleur)
    {
        super(uno, couleur);
    }

    public String getNom() {
        return "ChangementDeSens";
    }

    @Override
    public void appliquerEffet() {
        getUno().inverserSens();

        System.out.println("Le sens est change!");
    }
}
