package glaces;
import geometrie.Point ;

/**
 * Un iceberg rectangulaire
 * @author Martine Gautier, Université de Lorraine, Denys SHCHERBA
 */
public class Iceberg2D {

    private Point enBasAGauche ;
    private Point enHautADroite ;

    /**
     * Construction
     * @param g le coin en bas à gauche
     * @param d le coin en haut à droite
     * uniquement en coordonnées positives
     */
    public Iceberg2D(Point g, Point d) {
        enBasAGauche = g;
        enHautADroite = d;
    }

    /**
     * Construction par fusion de deux icebergs qui se touchent
     * @param i1 premier iceberg à fusionner
     * @param i2 deuxième iceberg à fusionner
     */
    public Iceberg2D(Iceberg2D i1, Iceberg2D i2) {
        if(i1.collision(i2))
        {
        enBasAGauche = new Point(Math.min(i1.enBasAGauche.getAbscisse(), i2.enBasAGauche.getAbscisse()),
        			      Math.min(i1.enBasAGauche.getOrdonnee(), i2.enBasAGauche.getOrdonnee()));
	enHautADroite = new Point(Math.max(i1.enHautADroite.getAbscisse(), i2.enHautADroite.getAbscisse()),
        			      Math.max(i1.enHautADroite.getOrdonnee(), i2.enHautADroite.getOrdonnee()));
        }
        else
        {
        enBasAGauche = i1.enBasAGauche;
        enHautADroite = i1.enHautADroite;
        }
    }

    /**
     * Retourne le coin en bas à gauche
     * @return le coin en bas à gauche
     */
    public Point coinEnBasAGauche() {
        return enBasAGauche ;
    }

    /**
     * Retourne le coin en haut à droite
     * @return le coin en haut à droite
     */
    public Point coinEnHautADroite() {
        return enHautADroite ;
    }


    /**
     * Retourne la hauteur
     * @return hauteur
     */
    public double hauteur() {
        return enHautADroite.getOrdonnee() - enBasAGauche.getOrdonnee() ;
    }

    /**
     * Retourne la largeur
     * @return largeur
     */
    public double largeur() {
        return enHautADroite.getAbscisse() - enBasAGauche.getAbscisse();
    }

    /**
     * Retourne la surface totale
     * @return surface totale
     */
    public double surface() {
        return hauteur() * largeur();
    }

    /**
     * Retourne vrai si il y a une collision entre les deux icebergs
     * @param i iceberg potentiellement en collision
     * @return vrai si collision entre les deux icebergs
     */
    public boolean collision(Iceberg2D i) {
        return this.enBasAGauche.getAbscisse() <= i.enHautADroite.getAbscisse() &&
               this.enHautADroite.getAbscisse() >= i.enBasAGauche.getAbscisse() &&
               this.enHautADroite.getOrdonnee() >= i.enBasAGauche.getOrdonnee() &&
               this.enBasAGauche.getOrdonnee() <= i.enHautADroite.getOrdonnee();
    }

    /**
     * Retourne vrai si this est plus volumineux que i
     * @param i iceberg à comparer
     * @return vrai si this est plus volumineux que i
     */
    public boolean estPlusGrosQue(Iceberg2D i) {
        return this.surface() > i.surface();
    }

    public String toString() {
        return enBasAGauche.toString() + " " + enHautADroite.toString();
    }

    /**
     * Retourne le point au centre
     * @return le point au centre de l'iceberg
     */
    public Point centre() {
        return new Point(Math.abs(enHautADroite.getAbscisse() -  enBasAGauche.getAbscisse())/2, Math.abs(enHautADroite.getOrdonnee() -  enBasAGauche.getOrdonnee())/2);
    }

    /**
     * Réduction dans les quatre directions ; le centre ne bouge pas
     * @param fr dans ]0..1[ facteur de réduction
     */
    public void fondre(double fr) {
        double hauteur = this.hauteur();
        double largeur = this.largeur();
        enHautADroite.deplacer(-largeur * fr / 2, 0);
        enBasAGauche.deplacer(largeur * fr / 2, 0);
        enHautADroite.deplacer(0, -hauteur * fr / 2);
        enBasAGauche.deplacer(0, hauteur * fr / 2);
    }

    /**
     * Casser une partie à droite
     * @param fr dans ]0..1[ facteur de réduction
     */
    public void casserDroite(double fr) {
        enHautADroite.deplacer(-largeur() * fr, 0);
    }

    /**
     * Casser une partie à gauche
     * @param fr dans ]0..1[ facteur de réduction
     */
    public void casserGauche(double fr) {
        enBasAGauche.deplacer(largeur() * fr, 0);
    }

    /**
     * Casser une partie en haut
     * @param fr dans ]0..1[ facteur de réduction
     */
    public void casserHaut(double fr) {
        enHautADroite.deplacer(0, -hauteur() * fr);
    }

    /**
     * Casser une partie en bas
     * @param fr dans ]0..1[ : définit le pourcentage supprimé
     */
    public void casserBas(double fr) {
        enBasAGauche.deplacer(0, hauteur() * fr);
    }

}

