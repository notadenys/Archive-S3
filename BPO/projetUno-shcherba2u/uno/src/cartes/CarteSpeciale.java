package src.cartes;

import src.jeu.Uno;


/**Plus4, Joker*/
public abstract class CarteSpeciale extends Carte{
    public CarteSpeciale(Uno uno){
        super(uno, 50);
    }


    public boolean peutEtreRecouvertePar(Carte c) {
        return c.peutEtrePoseeSur(this);
    }

    public boolean peutEtrePoseeSur(Chiffre c) {
        return true;
    }
    public boolean peutEtrePoseeSur(CarteAction c) {
        return true;
    }
    public boolean peutEtrePoseeSur(CarteSpeciale c) {
        return true;
    }
}
