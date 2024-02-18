package glaces;
import glaces.Ocean;
import glaces.Pingouin;
import glaces.ArcticImage;
import java.util.Scanner;


/**
 * Le jeu, classe principale
 * @author Denys SHCHERBA
 */
public class Jeu
{
	private Ocean ocean;
	private Pingouin pingouin;
	private Poisson[] poissons;
	private ArcticImage image;
	private int hungerCount;
	
	public Jeu()
	{
		ocean = new Ocean(20, 700, 700);
		pingouin = new Pingouin(30, ocean);
		image = new ArcticImage(700, 700);
		poissons = new Poisson[5];
		for (int i = 0; i < 5; i++)
		{
			poissons[i] = new Poisson(10, 20, ocean);
		}
	}
	
	public void jouer()
	{
		Scanner scanner = new Scanner(System.in);
		int fondreCount = 0;
		hungerCount = 0;
		game: while(true)
		{
			if (hungerCount >= 5) pingouin.hungry();
			int[][] img = pingouin.getColors(ocean.getColors());
			for (int i = 0; i < 5; i++)
			{
				if(poissons[i].getIsAlive()) img = poissons[i].getColors(img);
			}
			image.setColors(img);
			
			
			
			System.out.println("Saisir z q s d ou Z Q S D pour se deplacer");
			String choice = scanner.nextLine();
			switch(choice.toUpperCase())
			{
				case "Z":
					if (ocean.getHeight() >= pingouin.getY() + pingouin.getTaille() + pingouin.getSpeed())
					{
						pingouin.moveUp(pingouin.getSpeed());
					}
					break;
				case "Q":
					if (0 <= pingouin.getX() - pingouin.getSpeed())
					{
						pingouin.moveLeft(50);
					}
					break;
				case "S":
					if (0 <= pingouin.getY() - pingouin.getSpeed())
					{
						pingouin.moveDown(pingouin.getSpeed());
					}
					break;
				case "D":
					if (ocean.getWidth() >= pingouin.getX() + pingouin.getTaille() + pingouin.getSpeed())
					{
						pingouin.moveRight(pingouin.getSpeed());
					}
					break;
				default:
					image.fermer();
					break game;
			}
			hungerCount++;
			
			for (int i = 0; i < 5; i++)
			{
				if(poissons[i].getIsAlive()) poissons[i].move(40, img);
				if(poissons[i].getIsAlive()) 
				{
					if(poissons[i].getReturnCount() >= 10) poissons[i].kill();
				}
				if(poissons[i].getIsAlive()) handleEating(i);
			}
			
			if (fondreCount % 5 == 0) ocean.fondre(0.1);
			fondreCount++;
			handleIcebergsCollision();
		}
	}
	
	private void handleEating(int i)
	{
	if (poissons[i].getX() + poissons[i].getWidth() > pingouin.getX() && poissons[i].getX() < pingouin.getX() + pingouin.getTaille() &&
            poissons[i].getY() + poissons[i].getHeight() > pingouin.getY() && poissons[i].getY() < pingouin.getY() + pingouin.getTaille())
	{
		
		poissons[i].kill();
		pingouin.feed();
		hungerCount = 0;
	}
	}
	
	private void handleIcebergsCollision()
	{
	for (int i = 0; i < ocean.getCount(); i++)
	{
		if (ocean.getIceberg(i).coinEnBasAGauche().getAbscisse() + ocean.getIceberg(i).largeur() > pingouin.getX() && ocean.getIceberg(i).coinEnBasAGauche().getAbscisse() < pingouin.getX() + pingouin.getTaille() &&
      		    ocean.getIceberg(i).coinEnBasAGauche().getOrdonnee() + ocean.getIceberg(i).hauteur() > pingouin.getY() && ocean.getIceberg(i).coinEnBasAGauche().getOrdonnee() < pingouin.getY() + pingouin.getTaille())

		{
			
			ocean.getIceberg(i).fondre(1);
		}
	}
	}
}
