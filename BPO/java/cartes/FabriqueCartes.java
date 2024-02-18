package cartes;

public class FabriqueCartes {
    private static FabriqueCartes instance = new FabriqueCartes();

    public static FabriqueCartes getInstance() {return instance;}

    private FabriqueCartes(){}

    public PaquetDeCartes getPaquet1Vert()
    {
        PaquetDeCartes pdc = new PaquetDeCartes();
        Carte carte = new Carte(5, Couleur.VERT);
        pdc.ajouter(carte);
        return pdc;
    }

    public PaquetDeCartes getPaquet32()
    {
        PaquetDeCartes pdc = new PaquetDeCartes();
        Carte carte = new Carte(5, Couleur.VERT);
        for (int i = 0; i < 28; i++)
        {
            pdc.ajouter(carte);
        }
        pdc.ajouter(new Carte(4, Couleur.JAUNE));
        pdc.ajouter(new Carte(3, Couleur.BLEU));
        pdc.ajouter(new Carte(2, Couleur.ROUGE));
        pdc.ajouter(new Carte(1, Couleur.JAUNE));
        return pdc;
    }
}
