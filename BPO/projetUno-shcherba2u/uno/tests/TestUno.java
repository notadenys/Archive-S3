package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.cartes.FabriqueCartes;
import src.jeu.Uno;

import static org.junit.jupiter.api.Assertions.*;

public class TestUno {
    Uno uno;
    FabriqueCartes singleton = FabriqueCartes.getInstance();

    @BeforeEach
    void init() {
        uno = new Uno();
    }

    @Test
    void creerLesJoueurs() {
        uno.creerLesJoueurs(5);
        assertEquals(6, uno.getNbJoueurs());
    }

    @Test
    void distribuerCartes() {
        uno.creerLesJoueurs(2);
        uno.distribuerCartes();
        assertEquals(uno.getJoueur(1).getMain().getNombreDeCartes(), 7);
        assertEquals(uno.getJoueur(2).getMain().getNombreDeCartes(), 7);
        assertEquals(uno.getPioche().getNombreDeCartes(), 86);
    }

    @Test
    void changerDeSens() {
        assertTrue(uno.getSens());
        uno.inverserSens();
        assertFalse(uno.getSens());
    }

    @Test
    void prochainJouer() {
        uno.creerLesJoueurs(3);
        uno.setJoueurQuiDistribue(3);
        uno.choisirQuiJoue();
        uno.prochainJouer();
        assertEquals(1, uno.getJoueurQuiJoue());
    }
}
