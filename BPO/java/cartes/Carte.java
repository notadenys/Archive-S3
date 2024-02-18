package cartes;

public class Carte {

    private int valeur;

    private Couleur couleur;

    public Carte(int valeur, Couleur couleur) {
        assert valeur > 0 : "Valeur est non valable";
        this.valeur = valeur;
        assert couleur != null : "Couleur est null";
        this.couleur = couleur;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        assert valeur > 0 : "Valeur est non valable";
        this.valeur = valeur;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        assert couleur != null : "Couleur est null";
        this.couleur = couleur;
    }

    public String toString() {
        return "Carte{" +
                "valeur=" + valeur +
                ", couleur=" + couleur +
                '}';
    }
}
