package src.jeu;

public class Jeu {
    public static void main(String[] args) {
        Uno uno = new Uno();
        DLC dlc = new DLC(uno);

        dlc.jouer(5);
    }
}
