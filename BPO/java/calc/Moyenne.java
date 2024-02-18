package calc ;

public class Moyenne extends Naire {
    public Moyenne (Expression... expression) {
        super(expression);
    }
    public int valeur() {
        int moyenne = 0;
        for (int i = 0; i < expressions.length; i++)
        {
            moyenne += expressions[i].valeur();
        }
        return moyenne/expressions.length;
    }

    public String getNom()
    {
        return "moyenne";
    }
}
