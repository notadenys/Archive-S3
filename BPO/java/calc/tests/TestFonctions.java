package calc.tests;

import calc.Expression;
import calc.Maximum;
import calc.Moyenne;
import calc.Minimum;
import calc.Nombre;
import calc.Somme;

public class TestFonctions {
    public static void main (String [] args) {
        Expression e1 = new Somme(new Nombre(5), new Nombre(6)); 
        assert (e1.valeur() == 11) : "Somme ne marche pas";
        Expression e2 = new Maximum(new Nombre(1), new Nombre(3), new Nombre(2));
        assert (e2.valeur() == 3) : "Maximum ne marche pas";
        Expression e3 = new Minimum(new Nombre(1), new Nombre(3), new Nombre(2));
        assert (e3.valeur() == 1) : "Minimum ne marche pas";
        Expression e4 = new Moyenne(new Nombre(1), new Nombre(3), new Nombre(2));
        assert (e4.valeur() == 2) : "Moyenne ne marche pas";
    }
}
