package glaces.tests;
import glaces.ArcticImage;
import glaces.Ocean;

import java.util.Scanner;


/**
 * Test file pour le classe Ocean
 * @author Denys
 * @version Septembre 2023
 */
 
public class TestOcean {
    public static void main(String[] args) {
    	testOcean();
    	testOceanConstVide();
    }
    
    public static void testOcean()
    {
    	Ocean oc = new Ocean(5, 500, 500);
    	System.out.println(oc.toString());
    	ArcticImage img = new ArcticImage(500, 500);
    	img.setColors(oc.getColors());
    	
    }
    
    public static void testOceanConstVide()
    {
    	Ocean oc = new Ocean();
    	System.out.println(oc.toString());
    	ArcticImage img = new ArcticImage(500, 500);
    	img.setColors(oc.getColors());
    }
}
