package src.cartes;

import src.jeu.Uno;

import java.util.Random;

public abstract class Carte {
    private Uno uno;
    private int valeur;
    private Couleur couleur;


    /**
     * constructor for cards without color
     * @param uno
     * @param valeur
     */
    public Carte(Uno uno, int valeur) {
        this.uno = uno;
        assert valeur > 0 : "Valeur est non valable";
        this.valeur = valeur;
    }

    /**
     * basic constructor for cards
     * @param uno
     * @param valeur
     * @param couleur
     */
    public Carte(Uno uno, int valeur, Couleur couleur) {
        this.uno = uno;
        assert valeur >= 0 : "Valeur est non valable";
        this.valeur = valeur;
        assert couleur != null : "Couleur est null";
        this.couleur = couleur;
    }


    public void setValeur(int valeur) {
        assert valeur >= 0 : "Valeur est non valable";
        this.valeur = valeur;
    }

    public void setCouleur(Couleur couleur) {
        assert couleur != null : "Couleur est null";
        this.couleur = couleur;
    }

    /**
     * allows you to pick a color for the card
     * @param col char for color
     * @throws IllegalArgumentException invalid character
     */
    public void choisirCouleur(char col) throws IllegalArgumentException
    {
        switch (col)
        {
            case 'r':
                setCouleur(Couleur.ROUGE);
                break;

            case 'b':
                setCouleur(Couleur.BLEU);
                break;

            case 'v':
                setCouleur(Couleur.VERT);
                break;

            case 'j':
                setCouleur(Couleur.JAUNE);
                break;

            default:
                throw new IllegalArgumentException("Invalid character");
        }
    }

    /**
     * allows to set a random color for a card
     */
    public void setRandomColeur()
    {
        int c = new Random().nextInt(4);
        switch (c)
        {
            case 0:
                setCouleur(Couleur.BLEU);
                break;
            case 1:
                setCouleur(Couleur.JAUNE);
                break;
            case 2:
                setCouleur(Couleur.ROUGE);
                break;
            case 3:
                setCouleur(Couleur.VERT);
                break;
        }

    }

    public int getValeur(){return valeur;}
    public Couleur getCouleur() {return couleur;}
    public Uno getUno(){return uno;}
    public String toString() {
        return getNom()+" {" +
                "valeur=" + valeur +
                ", couleur=" + couleur +
                '}';
    }

    /**
     * @return nom et couleur de la carte (si Chiffre on redefinit pour afficher la valeur aussi)
     */
    public String getAffichage() {return getNom() + " " + getCouleur();}

    public boolean estSansCouleur() {
        return couleur == null;
    }
    public boolean estDeCouleurCompatibleAvec(Carte c) {
        assert !c.estSansCouleur() : "carte c est sans couleur";
        return c.couleur == couleur;
    }

    public abstract String getNom();
    public abstract boolean peutEtreRecouvertePar(Carte c);
    public abstract boolean peutEtrePoseeSur(Chiffre c);
    public abstract boolean peutEtrePoseeSur(CarteAction c);
    public abstract boolean peutEtrePoseeSur(CarteSpeciale c);
    public abstract void appliquerEffet();
}
