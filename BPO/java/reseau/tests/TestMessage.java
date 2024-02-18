package reseau.tests;

import reseau.Message;
import reseau.adresses.Adresse ;
import reseau.adresses.Octet ;

public class TestMessage {

        public static void main(String[] args) {
            testMessage1();
            testMessage2();
            testMessage3();
            testMessage4();
            testMessage5();
            testAjouter();
            testAjouterOctet();
            testAjouterMessage();
            testExtraireEntier();
            testExtraireAdresse();
            testExtraireChaine();
            testAugmenter();
            testSupprimer();
            testSupprimerRange();
        }

    private static void testMessage1() 
    {
        Message m = new Message() ;
        assert (m != null) : "Erreur dans le constructeur" ;
    }

    private static void testMessage2() 
    {
        Message m1 = new Message(1,255) ;
        Message m2 = new Message(1,255) ;
        assert (m1.toString().equals(m2.toString())) : "Erreur dans le constructeur sur int" ;
    }

    private static void testMessage3() 
    {
        Message m = new Message("255") ;

        assert (m.toString().equals("255")) : "Erreur dans le constructeur sur String" ;
    }

    private static void testMessage4() 
    {
        Message m = new Message(111) ;

        assert (m.toString().equals("111")) : "Erreur dans le constructeur sur Message" ;
    }

    private static void testMessage5() 
    {
        Adresse a = new Adresse(8, 4);
        Message m = new Message(a) ;
        assert (m.toString().equals("240")) : "Erreur dans le constructeur sur Adresse" ;
    }

    private static void testAjouter() 
    {
        Message m = new Message(1) ;
        m.ajouter(255) ;
        Message m2 = new Message(1,255) ;
        assert (m.toString().equals(m2.toString())) : "Erreur dans la fonction ajouter(int)" ;
    }

    private static void testAjouterOctet() 
    {
        Message m = new Message(100) ;
        m.ajouter(new Octet(111)) ;
        assert (m.toString().equals("100.111")) : "Erreur dans la fonction ajouter(Octet)" ;
    }

    private static void testAjouterMessage() 
    {
        Message m = new Message(100) ;
        m.ajouter(new Message(111)) ;
        assert (m.toString().equals("100.111")) : "Erreur dans la fonction ajouter(Message)" ;
    }

    private static void testExtraireEntier() {
        Message m = new Message("111.110");
        int i = m.extraireEntier(0);
        assert (i == 221) : "Erreur dans la fonction extraireEntier()" ;
    }

    private static void testExtraireAdresse() {
        Message m = new Message("111.101.100.111");
        Adresse a = m.extraireAdresse(4);
        assert (a.toString().equals("111.101.100.111")) : "Erreur dans la fonction extraireAdresse()" ;
    }

    private static void testExtraireChaine() {
        Message m = new Message("108.111.108");
        String s = m.extraireChaine();
        assert (s.equals("lol")) : "Erreur dans la fonction extraireChaine()" ;
    }

    private static void testAugmenter() {
        Message m = new Message("111");
        m.augmenter(1, 100, 200);
        assert (m.toString().equals("112")) : "Erreur dans la fonction augmenter()" ;
    }

    private static void testSupprimer() {
        Message m = new Message("111.111.111.111");
        m.supprimer(0);
        assert (m.toString().equals("111.111.111")) : "Erreur dans la fonction supprimer(int i)";
    }

    private static void testSupprimerRange() {
        Message m = new Message("111.110.111.100");
        m.supprimer(0, 2);
        assert (m.toString().equals("100")) : "Erreur dans la fonction supprimer(int debut, int fin)";
    }
}

