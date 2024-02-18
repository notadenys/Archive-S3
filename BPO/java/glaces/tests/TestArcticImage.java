package glaces.tests;
import glaces.ArcticImage;


/**
 * Test file pour le classe ArcticImage
 * @author Denys
 * @version Septembre 2023
 */
 
public class TestArcticImage {
    public static void main(String[] args) {
    	int[][] tab = new int[300][300];
    	for (int i = 0; i < 300; i++)
    	{
    	for (int j = 0; j < 300; j++)
    	{
    	tab[i][j] = j % 2;  // on met 0 si i est pair, 1 si impair
    	}
    	}
    	ArcticImage img = new ArcticImage(300, 300);
    	img.setColors(tab);
    }
}
