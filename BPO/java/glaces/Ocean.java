package glaces;
import geometrie.Point ;
import glaces.Iceberg2D;
import java.util.Random;

/**
 * Un ocean
 * @author Denys SHCHERBA
 */
public class Ocean {

    private int largeur ;
    private int hauteur ;
    private int nombre ;
    private Iceberg2D[] icebergs;

    public Ocean(int nb, int l, int h) {
        largeur = l;
        hauteur = h;
        nombre = nb;
        Random g = new Random();
        
        icebergs = new Iceberg2D[nb];
        for (int i = 0; i < nb; i++)
        {
        int x = g.nextInt(l);
        int y = g.nextInt(h);
        int width = g.nextInt(x) % 200;
        int height = g.nextInt(y) % 200;
        icebergs[i] = new Iceberg2D(new Point(x-width, y-height), new Point(x, y));
        }
    }
    
    public Ocean()
    {
    	this(2, 300, 300);
    }
    
    
    public int getCount()
    {
    	return nombre;
    }
    
    public int getHeight()
    {
    	return hauteur;
    }
    
    public int getWidth()
    {
    	return largeur;
    }
    
    public Iceberg2D getIceberg(int i)
    {
    	return icebergs[i];
    }
    
    public void fondre(double fr)
    {
    	for (Iceberg2D iceberg : icebergs)
    	{
    		iceberg.fondre(fr);
    	}
    }

    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Ocean de la taille ").append(largeur).append("x").append(hauteur);
    for (int i = 0; i < nombre; i++)
    {
	sb.append(System.lineSeparator()).append("Iceberg ").append(i).append(" : ").append(icebergs[i].toString());
    }
    return sb.toString();
    }
    
    public int[][] getColors()
    {
    int[][] image =  new int[largeur][hauteur];
    for (int i = 0; i < largeur; i++)
	{
	    	for (int j = 0; j < hauteur; j++)
	    	{
	    		image[i][j] = 0;
	    	}
	}   	
	for (int c = 0; c < nombre; c++)
	{
	    	for (int j = (int)icebergs[c].coinEnBasAGauche().getOrdonnee(); j < (int)icebergs[c].coinEnHautADroite().getOrdonnee(); j++)
	    	{
		    	for (int i = (int)icebergs[c].coinEnBasAGauche().getAbscisse(); i < (int)icebergs[c].coinEnHautADroite().getAbscisse(); i++)
		    	{
		    		image[i][j] = 1;
		    	}
	    	}
	}
    return image;
    }
}

