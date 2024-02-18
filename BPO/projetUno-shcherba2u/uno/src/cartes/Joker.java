package src.cartes;

import src.jeu.Uno;

public class Joker extends CarteSpeciale {
    public Joker(Uno uno){
        super(uno);
    }

    public String getNom() {
        return "Joker";
    }

    public void appliquerEffet(){if(estSansCouleur()) setRandomColeur();}
}
