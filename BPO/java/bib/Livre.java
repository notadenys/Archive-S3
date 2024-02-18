package bib;

import java.util.Iterator;

public class Livre implements Iterable<String>{
    private Lexique angalis;
    private Lexique francais;
    private String titre;

    public Livre(String tit, Lexique ang, Lexique fr)
    {
        titre = tit;
        angalis = ang;
        francais = fr;
    }

    public Iterator<String> iterator()
    {
        return new Iterator<String>() {
            private boolean isTitreOut = false;

            Iterator<String> iteratorAnglais = angalis.iterator();
            Iterator<String> iteratorFrancais = francais.iterator();

            @Override
            public boolean hasNext() {
                return  iteratorFrancais.hasNext() || iteratorAnglais.hasNext();
            }

            @Override
            public String next() {
                if(!isTitreOut)
                {
                    isTitreOut = true;
                    return titre;
                }
                return iteratorFrancais.hasNext() ? iteratorFrancais.next() : iteratorAnglais.next();
            }
        };
    }
}
