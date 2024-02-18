package tests;

import org.junit.jupiter.api.Test;
import src.cartes.ChangementDeSens;
import src.cartes.Chiffre;
import src.cartes.Joker;
import src.cartes.Couleur;
import src.jeu.Uno;

import static org.junit.jupiter.api.Assertions.*;

class JokerTest {

    @Test
    void peutEtreRecouvertePar() {
        Joker c1 = new Joker(new Uno());
        c1.setCouleur(Couleur.JAUNE);
        ChangementDeSens c2 = new ChangementDeSens(new Uno(), Couleur.JAUNE);
        assertTrue(c1.peutEtreRecouvertePar(c2));
    }

    @Test
    void testPeutEtrePoseeSurCh() {
        Joker c1 = new Joker(new Uno());
        Chiffre c2 = new Chiffre(new Uno(), 0, Couleur.JAUNE);
        assertTrue(c1.peutEtrePoseeSur(c2));
    }

    @Test
    void testPeutEtrePoseeSurCA() {
        Joker c1 = new Joker(new Uno());
        ChangementDeSens c2 = new ChangementDeSens(new Uno(), Couleur.JAUNE);
        assertTrue(c1.peutEtrePoseeSur(c2));
    }

    @Test
    void testPeutEtrePoseeSurCS() {
        Joker c1 = new Joker(new Uno());
        Joker c2 = new Joker(new Uno());
        assertTrue(c1.peutEtrePoseeSur(c2));
    }

    @Test
    void testSetCouleur() {
        Joker c = new Joker(new Uno());
        c.setCouleur(Couleur.BLEU);
        assertEquals(Couleur.BLEU, c.getCouleur());
    }

    @Test
    void testToString() {
        Joker c = new Joker(new Uno());
        c.setCouleur(Couleur.BLEU);
        assertEquals("Joker {valeur=50, couleur=Bleu}", c.toString());
    }

    @Test
    void estSansCouleur() {
        Joker c = new Joker(new Uno());
        assertTrue(c.estSansCouleur());
    }

    @Test
    void estDeCouleurCompatibleAvec() {
        Joker c1 = new Joker(new Uno());
        c1.setCouleur(Couleur.BLEU);
        Chiffre c2 = new Chiffre(new Uno(), 10, Couleur.VERT);
        assertFalse(c1.estDeCouleurCompatibleAvec(c2));
    }

    @Test
    void testGetNom() {
        Joker c = new Joker(new Uno());
        assertEquals("Joker", c.getNom());
    }
}