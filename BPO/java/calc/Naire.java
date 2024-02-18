package calc ;

public abstract class Naire implements Expression {
    protected Expression[] expressions;
    
    public Naire (Expression... expression) {
        assert (expression != null) : "Un des operateurs est null";
        expressions = new Expression[expression.length];
        for (int i = 0; i < expression.length; i++)
        {
            expressions[i] = expression[i];
        }
    }
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(getNom()).append("(");
        for (int i = 0; i < expressions.length; i++)
        {
            string.append(expressions[i].valeur()).append(", ");
        }
        string.deleteCharAt(string.length());
        string.append(")");
        return string.toString();
    }

    public abstract String getNom() ;
}
