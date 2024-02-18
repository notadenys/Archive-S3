package calc ;

public class Quotient extends Binaire {
    public Quotient (Expression g, Expression d) {
        super(g, d) ;
    }
    public int valeur() {
        return gauche.valeur() / droit.valeur() ;
    }
    public String getOperateur() {
        return "/" ;
    }
}
