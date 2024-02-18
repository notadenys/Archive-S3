package lanceur ;

import java.lang.reflect.* ;
import java.util.Arrays;


/**
 * La classe LanceurJeu permet de démarrer un jeu à deux joueurs, défini par une classe possédant
 * - un constructeur avec 0, .. k entiers en paramètres
 * - une méthode void unePartie()
 */
public class LanceurJeu {
    private final Class<?> aClass;
    private final Object jeu;

    public LanceurJeu (String[] args) throws ExceptionJeu{
        // chercher la classe de jeu
        if (args.length == 0)
        {
            throw new ExceptionJeu("Le nom de classe est vide");
        }
        try {
            aClass = Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            throw new ExceptionJeu("Classe de jeu non trouvée");
        }


        // construire le jeu
        Object[] arguments = new Object[args.length - 1];
        for (int i = 1; i < args.length; i++)
        {
            arguments[i-1] = Integer.parseInt(args[i]);
        }

        Class<?>[] classes = new Class<?>[arguments.length];
        Arrays.fill(classes, int.class);
        try {
            Constructor<?> aConstructor = aClass.getConstructor(classes);
            jeu = aConstructor.newInstance(arguments);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
        {
            throw new ExceptionJeu("Instanciation impossible");
        }
    }

    public void jouer() throws ExceptionJeu {
        Method unePartie;
        try {
            unePartie = aClass.getMethod("unePartie");
        } catch (NoSuchMethodException e) {
            throw new ExceptionJeu("Le methode unePartie non trouve");
        }

        try {
            unePartie.invoke(jeu);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new ExceptionJeu("Le methode unePartie() n'est pas applicable");
        }
    }
    public static void main(String[] args ) {
        try {
            LanceurJeu j = new LanceurJeu(args);
            j.jouer() ;
        } catch (ExceptionJeu e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Jeu terminé");
    }
}
