package src.cartes;

import src.jeu.Uno;

public class Plus2 extends CarteAction {
    public Plus2(Uno uno, Couleur couleur)
    {
        super(uno, couleur);
    }

    public String getNom() {
        return "Plus2";
    }

    @Override
    public void appliquerEffet() {
        getUno().prochainJouer();

        getUno().getJoueurCourant().piocher();
        getUno().getJoueurCourant().piocher();

        System.out.println(getUno().getNomDeJoueurCourant() + " prends 2 cartes et passes son tour");
    }
}
