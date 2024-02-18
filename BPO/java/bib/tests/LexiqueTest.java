package bib.tests;

import bib.Lexique;
import org.junit.Test;

import java.util.Iterator;

public class LexiqueTest {
    @Test
    public void testForEach()
    {
        Lexique lex = new Lexique(3);
        try
        {
            lex.ajouter("abc");
            lex.ajouter("def");
            lex.ajouter("ghi");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        for(Object str : lex)
        {
            System.out.println(str);
        }
    }

    @Test
    public void testWhile()
    {
        Lexique lex = new Lexique(3);
        try
        {
            lex.ajouter("abc");
            lex.ajouter("def");
            lex.ajouter("ghi");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        Iterator<String> iter = lex.iterator();
        while (iter.hasNext())
        {
            System.out.println(iter.next());
        }
    }
}
