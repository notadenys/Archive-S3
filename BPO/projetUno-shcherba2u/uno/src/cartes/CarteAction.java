package src.cartes;

import src.jeu.Uno;

/**PasseTonTour, Plus2, ChangementDeSens*/
public abstract class CarteAction extends Carte {
    public CarteAction(Uno uno, Couleur couleur)
    {
        super(uno, 20, couleur);
    }

    public boolean peutEtreRecouvertePar(Carte c) {
        return c.peutEtrePoseeSur(this);
    }

    public boolean peutEtrePoseeSur(Chiffre c) {
        return this.estDeCouleurCompatibleAvec(c);
    }
    public boolean peutEtrePoseeSur(CarteAction c) {
        return this.estDeCouleurCompatibleAvec(c);
    }
    public boolean peutEtrePoseeSur(CarteSpeciale c) {
        return this.estDeCouleurCompatibleAvec(c);
    }
}
