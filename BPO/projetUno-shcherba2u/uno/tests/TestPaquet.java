package tests;

import org.junit.Test;
import src.erreurs.ErreurFichier;
import src.cartes.*;
import src.jeu.Uno;


public class TestPaquet {
    @Test
    public void testUno()
    {
        FabriqueCartes fc = FabriqueCartes.getInstance();
        Uno uno = new Uno();
        PaquetDeCartes pdc = fc.getPaquetUno(uno);
        System.out.println(pdc);
        System.out.println(pdc.getNombreDeCartes());
        try {
            pdc.ecrire("cartes.txt");
        }
        catch (ErreurFichier e){System.out.println(e.getMessage());}
    }

    @Test
    public void testLire()
    {
        PaquetDeCartes pdc = new PaquetDeCartes();
        try {
            pdc.lire("cartes.txt");
        }
        catch (ErreurFichier e){System.out.println(e.getMessage());}

        System.out.println(pdc);
        System.out.println(pdc.getNombreDeCartes());
    }

    @Test
    public void testIterator()
    {
        PaquetDeCartes pdc = new PaquetDeCartes();
        Uno uno = new Uno();
        pdc.ajouter(new Joker(uno), new Plus4(uno));
        for (Carte c : pdc)
        {
            System.out.println(c.toString());
        }
    }

    @Test
    public void testGetRandomCouleur()
    {
        Joker j = new Joker(new Uno());
        for (int i = 0; i < 10; i++)
        {
            j.setRandomColeur();
            System.out.println(j);
        }
    }
}
