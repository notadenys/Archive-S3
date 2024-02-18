package calc ;

public class Maximum extends Naire {
    public Maximum (Expression... expression) {
        super(expression);
    }
    public int valeur() {
        int max = expressions[0].valeur();
        for (int i = 1; i < expressions.length; i++)
        {
            max = (max < expressions[i].valeur()) ? expressions[i].valeur() : max;
        }
        return max;
    }

    public String getNom()
    {
        return "max";
    }
}
