package calc ;

public class Minimum extends Naire {
    public Minimum (Expression... expression) {
        super(expression);
    }
    public int valeur() {
        int min = expressions[0].valeur();
        for (int i = 1; i < expressions.length; i++)
        {
            min = (min > expressions[i].valeur()) ? expressions[i].valeur() : min;
        }
        return min;
    }

    public String getNom()
    {
        return "min";
    }
}
