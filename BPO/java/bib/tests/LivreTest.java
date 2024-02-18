package bib.tests;

import bib.Lexique;
import bib.Livre;
import org.junit.Test;

public class LivreTest {
    @Test
    public void testLivre()
    {
        Lexique fr = new Lexique(3);
        Lexique eng = new Lexique(3);
        try
        {
            fr.ajouter("cafe");
            fr.ajouter("bonjour");
            eng.ajouter("coffee");
            eng.ajouter("hello");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        Livre livre = new Livre("Book", eng, fr);

        for(Object str : livre)
        {
            System.out.println(str);
        }
    }
}
