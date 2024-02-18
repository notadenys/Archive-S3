package bib;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Lexique implements Iterable{
    private  int nbMots;
    private String[] mots;

    public Lexique(int capacite)
    {
        nbMots = 0;
        mots = new String[capacite];
    }

    public Iterator<String> iteratorList()
    {
        List<String> li = Arrays.asList(mots);
        return li.iterator();
    }

    @Override
    public Iterator<String> iterator()
    {
        return new Iterator<String>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < nbMots;
            }

            @Override
            public String next() {
                return mots[index++];
            }
        };
    }

    public void ajouter(String mot) throws Exception
    {
        if (nbMots == mots.length)
        {
            throw new Exception("capacité est atteinte");
        }
        mots[nbMots] = mot;
        nbMots++;
    }

    public int nbMots()
    {
        return nbMots;
    }

    public int getCapacite()
    {
        return mots.length;
    }

    @Override
    public String toString()
    {
        StringBuilder strb = new StringBuilder();
        for (String a : mots)
        {
            strb.append(a).append(" ");
        }
        strb.deleteCharAt(strb.length()-1);
        return strb.toString();
    }
}
