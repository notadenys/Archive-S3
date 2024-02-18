package cartes.tests;

import cartes.FabriqueCartes;
import cartes.PaquetDeCartes;

public class TestPaquet {
    public static void main(String[] args)
    {
        test1Vert();
        test32();
    }

    public static void test1Vert()
    {
        FabriqueCartes fc = FabriqueCartes.getInstance();
        PaquetDeCartes pdc = fc.getPaquet1Vert();
        System.out.println(pdc.toString());
        System.out.println(pdc.getNombreDeCartes());
    }

    public static void test32()
    {
        FabriqueCartes fc = FabriqueCartes.getInstance();
        PaquetDeCartes pdc = fc.getPaquet32();
        System.out.println(pdc.toString());
        System.out.println(pdc.getNombreDeCartes());
        pdc.retourner();
        System.out.println(pdc.toString());
        pdc.melanger();
        System.out.println(pdc.toString());
    }
}
