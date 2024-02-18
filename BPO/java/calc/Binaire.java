package calc ;

public abstract class Binaire implements Expression {
    protected Expression gauche ;
    protected Expression droit ;
    
    public Binaire (Expression g, Expression d) {
        assert (g != null && d != null) : "Un des operateurs est null";
        this.gauche = g ;
        this.droit = d ;
    }
    public String toString () {
        return "("+gauche+getOperateur()+droit+")" ;
    }
    public abstract String getOperateur() ;
}
