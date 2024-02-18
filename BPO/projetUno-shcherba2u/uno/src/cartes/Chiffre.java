package src.cartes;

import src.jeu.Uno;

public class Chiffre extends Carte {
    public Chiffre(Uno uno, int valeur, Couleur couleur) {
        super(uno, valeur, couleur);
    }


    public String getNom() {
        return "Chiffre";
    }

    @Override
    public String getAffichage() {return getValeur() + " " + getCouleur();}

    public boolean peutEtreRecouvertePar(Carte c) {
        return c.peutEtrePoseeSur(this);
    }

    public boolean peutEtrePoseeSur(Chiffre c) {return this.getValeur() == c.getValeur() || this.estDeCouleurCompatibleAvec(c);}
    public boolean peutEtrePoseeSur(CarteSpeciale c) {
        return this.estDeCouleurCompatibleAvec(c);
    }
    public boolean peutEtrePoseeSur(CarteAction c) {
        return this.estDeCouleurCompatibleAvec(c);
    }

    public void appliquerEffet(){}
}
