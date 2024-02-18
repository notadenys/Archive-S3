package reseau.tests;

import reseau.adresses.Octet;
import reseau.adresses.Adresse;

public class TestAdresse {
    public static void main(String[] args) {
        testGetNbreOctetsAdresse() ;
        testGetOctet() ;
        testSetOctet() ;
        testToStringAdresse();
        testInverser();
    }

    private static void testGetNbreOctetsAdresse() {
        Adresse a ;
        int res ;

        // adresse créée vide
        a = new Adresse(16) ;
        res = a.getNbreOctets() ;
        assert(res==2):"Nombre d'octets incorrect" ;
    }

    private static void testToStringAdresse()
    {
        Adresse a ;
        String res ;

        // adresse créée vide
        a = new Adresse("255.17.65.0");
        res = a.toString() ;
        assert(res.equals("255.17.65.0")): "Erreur dans la fonction toString()" ;
    }

    private static void testInverser()
    {
        Adresse a ;
        String res ;

        // adresse créée vide
        a = new Adresse(16, 15);
        a.inverser();
        res = a.toString();
        assert(res.equals("0.1")): "Erreur dans la fonction inverser" ;
    }


    private static void testGetOctet()
    {
        Adresse a ;
        String res ;

        // adresse créée vide
        a = new Adresse(new Octet(111), new Octet(101));
        res = a.getOctet(1).toString();
        assert(res.equals("101")): "Erreur dans la fonction getOctet" ;
    }

    private static void testSetOctet()
    {
        Adresse a ;
        String res ;

        // adresse créée vide
        a = new Adresse(16);
        a.setOctet(new Octet(111), 1);
        res = a.getOctet(1).toString();
        assert(res.equals("111")): "Erreur dans la fonction setOctet" ;
    }
}
