package tests;

import org.junit.jupiter.api.Test;
import src.cartes.*;
import src.jeu.Uno;

import static org.junit.jupiter.api.Assertions.*;

class Plus2Test {

    @Test
    void peutEtreRecouvertePar() {
        Plus2 c1 = new Plus2(new Uno(), Couleur.BLEU);
        Joker c2 = new Joker(new Uno());
        assertTrue(c1.peutEtreRecouvertePar(c2));
    }

    @Test
    void testPeutEtrePoseeSurCh() {
        Plus2 c1 = new Plus2(new Uno(), Couleur.JAUNE);
        Chiffre c2 = new Chiffre(new Uno(), 0, Couleur.JAUNE);
        assertTrue(c1.peutEtrePoseeSur(c2));
    }

    @Test
    void testPeutEtrePoseeSurCA() {
        Plus2 c1 = new Plus2(new Uno(), Couleur.JAUNE);
        ChangementDeSens c2 = new ChangementDeSens(new Uno(), Couleur.JAUNE);
        assertTrue(c1.peutEtrePoseeSur(c2));
    }

    @Test
    void testPeutEtrePoseeSurCS() {
        Plus2 c1 = new Plus2(new Uno(), Couleur.JAUNE);
        Joker c2 = new Joker(new Uno());
        c2.setCouleur(Couleur.JAUNE);
        assertTrue(c1.peutEtrePoseeSur(c2));
    }

    @Test
    void testSetCouleur() {
        Plus2 c = new Plus2(new Uno(), Couleur.JAUNE);
        c.setCouleur(Couleur.BLEU);
        assertEquals(Couleur.BLEU, c.getCouleur());
    }
    @Test
    void testToString() {
        Plus2 c = new Plus2(new Uno(), Couleur.JAUNE);
        assertEquals("Plus2 {valeur=20, couleur=Jaune}", c.toString());
    }

    @Test
    void estSansCouleur() {
        Plus2 c = new Plus2(new Uno(), Couleur.JAUNE);
        assertFalse(c.estSansCouleur());
    }

    @Test
    void estDeCouleurCompatibleAvec() {
        Plus2 c1 = new Plus2(new Uno(), Couleur.JAUNE);
        Chiffre c2 = new Chiffre(new Uno(), 10, Couleur.BLEU);
        assertFalse(c1.estDeCouleurCompatibleAvec(c2));
    }

    @Test
    void testGetNom() {
        Plus2 c = new Plus2(new Uno(), Couleur.JAUNE);
        assertEquals("Plus2", c.getNom());
    }
}