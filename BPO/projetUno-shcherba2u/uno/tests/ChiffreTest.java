package tests;

import org.junit.jupiter.api.Test;
import src.cartes.*;
import src.jeu.Uno;

import static org.junit.jupiter.api.Assertions.*;

class ChiffreTest {

    @Test
    void testSetValeur() {
        Chiffre c = new Chiffre(new Uno(), 0, Couleur.ROUGE);
        c.setValeur(5);
        assertEquals(5, c.getValeur());
    }

    @Test
    void testSetCouleur() {
        Chiffre c = new Chiffre(new Uno(), 0, Couleur.ROUGE);
        c.setCouleur(Couleur.BLEU);
        assertEquals(Couleur.BLEU, c.getCouleur());
    }


    @Test
    void testToString() {
        Chiffre c = new Chiffre(new Uno(), 9, Couleur.JAUNE);
        assertEquals("Chiffre {valeur=9, couleur=Jaune}", c.toString());

    }

    @Test
    void testEstSansCouleur() {
        Chiffre c = new Chiffre(new Uno(), 9, Couleur.JAUNE);
        assertFalse(c.estSansCouleur());
    }

    @Test
    void testEstDeCouleurCompatibleAvecFalse() {
        Chiffre c1 = new Chiffre(new Uno(), 9, Couleur.JAUNE);
        Chiffre c2 = new Chiffre(new Uno(), 10, Couleur.VERT);
        assertFalse(c1.estDeCouleurCompatibleAvec(c2));
    }

    @Test
    void testEstDeCouleurCompatibleAvecTrue() {
        Chiffre c1 = new Chiffre(new Uno(), 9, Couleur.JAUNE);
        Chiffre c2 = new Chiffre(new Uno(), 10, Couleur.JAUNE);
        assertTrue(c1.estDeCouleurCompatibleAvec(c2));
    }

    @Test
    void testGetNom() {
        Chiffre c = new Chiffre(new Uno(), 9, Couleur.JAUNE);
        assertEquals("Chiffre", c.getNom());
    }

    @Test
    void testPeutEtreRecouvertePar() {
        Chiffre c1 = new Chiffre(new Uno(), 9, Couleur.JAUNE);
        ChangementDeSens c2 = new ChangementDeSens(new Uno(), Couleur.JAUNE);
        assertTrue(c1.peutEtreRecouvertePar(c2));
    }

    @Test
    void testPeutEtrePoseeSurChVal() {
    Chiffre c1 = new Chiffre(new Uno(), 9, Couleur.JAUNE);
    Chiffre c2 = new Chiffre(new Uno(), 9, Couleur.BLEU);
    assertTrue(c1.peutEtrePoseeSur(c2));
    }
    @Test
    void testPeutEtrePoseeSurChCol() {
        Chiffre c1 = new Chiffre(new Uno(), 9, Couleur.JAUNE);
        Chiffre c2 = new Chiffre(new Uno(), 0, Couleur.JAUNE);
        assertTrue(c1.peutEtrePoseeSur(c2));
    }

    @Test
    void testPeutEtrePoseeSurCA() {
        Chiffre c1 = new Chiffre(new Uno(), 9, Couleur.JAUNE);
        ChangementDeSens c2 = new ChangementDeSens(new Uno(), Couleur.JAUNE);
        assertTrue(c1.peutEtrePoseeSur(c2));
    }

    @Test
    void testPeutEtrePoseeSurCS() {
        Chiffre c1 = new Chiffre(new Uno(), 9, Couleur.JAUNE);
        Joker c2 = new Joker(new Uno());
        c2.setCouleur(Couleur.JAUNE);
        assertTrue(c1.peutEtrePoseeSur(c2));
    }
}