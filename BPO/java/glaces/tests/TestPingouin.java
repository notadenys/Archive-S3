package glaces.tests;
import glaces.ArcticImage;
import glaces.Ocean;
import glaces.Pingouin;
import java.util.Scanner;


/**
 * Test file pour le classe Pingouin
 * @author Denys
 * @version Septembre 2023
 */
 
public class TestPingouin {
    public static void main(String[] args) {
    	test();
    }
    
    static void test()
    {
    	Ocean oc = new Ocean(5, 500, 500);
    	oc.fondre(0.5);
    	Pingouin ping = new Pingouin(50, oc);
    	ping.moveUp(100);
    	ping.moveLeft(100);
    	ArcticImage img = new ArcticImage(500, 500);
    	img.setColors(ping.getColors(oc.getColors()));
    }
}
