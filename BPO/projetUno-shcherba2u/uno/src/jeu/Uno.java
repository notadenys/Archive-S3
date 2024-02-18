package src.jeu;

import src.erreurs.InvalidCoupExtension;
import src.cartes.Carte;
import src.cartes.FabriqueCartes;
import src.cartes.PaquetDeCartes;

import java.util.ArrayList;

public class Uno {
    private boolean sens;
    private boolean fin;
    private boolean finJeu;

    private DLC dlc;

    private int joueurQuiJoue;
    private int joueurQuiDistribue;

    private ArrayList<Joueur> joueurs;
    private PaquetDeCartes talon;
    private PaquetDeCartes pioche;

    public Uno()
    {
        sens = true;
        fin = false;
        finJeu = false;
    }

    /**
     * calls jouer() of the current player
     */
    public void jouer()
    {
        try {
            getJoueurCourant().jouer();
        } catch (InvalidCoupExtension e){
            System.out.println(e.getMessage());
        }

        handleFin();
        prochainJouer();
    }

    /**
     * initialises players and distributes cards
     * @param nbBots amount of bots
     */
    public void initialiser(int nbBots)
    {
        creerLesJoueurs(nbBots);
        choisirQuiDistribue();
        choisirQuiJoue();
        distribuerCartes();

        dlc.reagir();
    }

    /**
     * creates players
     * @param nbBots amount of bots
     */
    public void creerLesJoueurs(int nbBots)
    {
        assert nbBots >= 1 : "Pas de bots";
        joueurs = new ArrayList<>(nbBots + 1);
        joueurs.add(new JoueurHumain(this, "Joueur"));
        for (int i = 0; i < nbBots; i++)
        {
            joueurs.add(new Bot(this, "Bot " + (i + 1)));
        }
    }

    public boolean getFin() {return this.fin;}
    public boolean getFinJeu() {return this.finJeu;}

    /**
     * controls the end of the party
     */
    public void handleFin()
    {
        if (getJoueurCourant().aMainVide())
        {
            setFin(true);
            handleFinJeu();
        }
    }

    /**
     * controls the end of the game
     */
    public void handleFinJeu()
    {
        for (int i = 0; i < getNbJoueurs(); i++)
        {
            if (joueurs.get(i).getTotalScore() > 500) {
                finJeu = true;
                joueurQuiJoue = i;
                break;
            }
        }

    }

    public int getNbJoueurs() {return joueurs.size();}
    public boolean getSens() {return sens;}
    public int getJoueurQuiDistribue(){return joueurQuiDistribue;}
    public Joueur getJoueurCourant(){return joueurs.get(joueurQuiJoue);}
    public int getJoueurQuiJoue(){return joueurQuiJoue;}
    public Joueur getJoueur(int index){return joueurs.get(index);}
    public String getNomDeJoueurCourant(){return getJoueurCourant().getNom();}

    public ArrayList<Joueur> getJoueurs() {return joueurs;}
    public PaquetDeCartes getTalon() {return talon;}
    public PaquetDeCartes getPioche() { return pioche;}

    public Carte getTopCarte(){return talon.getSommet();}


    public void choisirQuiDistribue() {
        joueurQuiDistribue = (int)(Math.random() * getNbJoueurs());
    }

    public void choisirQuiJoue() {
        joueurQuiJoue = getJoueurQuiDistribue() == getNbJoueurs()-1 ? 0 : getJoueurQuiDistribue() + 1;
    }

    /**
     * distributes all the cards to players, pioche and talon
     */
    public void distribuerCartes()
    {
        FabriqueCartes singleton = FabriqueCartes.getInstance();
        talon = singleton.getPaquetVide();
        pioche = singleton.getPaquetUno(this);
        pioche.melanger();

        for(Joueur j : joueurs)
        {
            for (int i = 0; i < 7; i++)
            {
                j.getMain().ajouter(pioche.piocher());
            }
        }
        talon.ajouter(pioche.piocher());
        getTopCarte().appliquerEffet();
    }

    public void setDLC(DLC d) {this.dlc = d;}
    public void setFin(boolean b) {this.fin = b;}
    public void setJoueurQuiDistribue(int index) {joueurQuiDistribue = index;}

    public void inverserSens(){sens = !sens;}

    /**
     * changes the index of the next player depending on sens value
     */
    public void prochainJouer()
    {
        if(!fin) {
            if(sens)
            {
                joueurQuiJoue = (joueurQuiJoue == getNbJoueurs()-1) ? 0 : joueurQuiJoue + 1;
            }
            else
            {
                joueurQuiJoue = (joueurQuiJoue == 0) ? getNbJoueurs()-1 : joueurQuiJoue - 1;
            }
        }
    }
}
