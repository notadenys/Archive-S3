package src.jeu;

// Dialogue ligne de commande
public class DLC {
    private Uno uno;

    public DLC(Uno u)
    {
        this.uno = u;
        this.uno.setDLC(this);
    }

    /**
     * method called in a while loop to control the game
     */
    public void reagir()
    {
        System.out.println("\nLa carte sur top est " + uno.getTopCarte().getAffichage());

        System.out.println("C'est le tour du " + uno.getNomDeJoueurCourant() + "\n");
        uno.jouer();
    }

    public void jouer(int nbbots)
    {
        uno.initialiser(nbbots);
        while (!uno.getFinJeu())
        {
            while (!uno.getFin())
            {
                reagir();
            }
            System.out.println(uno.getNomDeJoueurCourant() + " a gagne cette main!");
            for (Joueur j : uno.getJoueurs())
            {
                j.updateTotalScore();
                j.afficherTotalScore();
            }
        }
        System.out.println(uno.getNomDeJoueurCourant() + " a gagne le jeu!");
    }
}
