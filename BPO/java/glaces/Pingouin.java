package glaces;
import glaces.Ocean;

/**
 * Un pingouin
 * @author Denys SHCHERBA
 */
public class Pingouin
{
	private int x;
	private int y;
	private int taille;
	private int vitesse;
	private boolean fatigue;
	
	public Pingouin(int taille, Ocean ocean)
	{
		this.taille = taille;
		this.x = ocean.getWidth() - taille;
		this.y = 0;
		this.vitesse = 50;
		this.fatigue = false;
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getTaille() {return taille;}
	public int getSpeed() {return vitesse;}
	
	public void moveRight(int dist)
	{
		x += dist;
	}
	
	public void moveLeft(int dist)
	{
		x -= dist;
	}
	
	public void moveUp(int dist)
	{
		y += dist;
	}
	
	public void moveDown(int dist)
	{
		y -= dist;
	}
	
	public void hungry()
	{
		fatigue = true;
		vitesse = 40;
	}
	
	public void feed()
	{
		fatigue = false;
		vitesse = 50;
	}
	
	public int[][] getColors(int[][] img)
	{
		int[][] image = img;
		for (int i = this.x; i < (this.x + this.taille); i++)
		{
			for (int j = this.y; j < (this.y + this.taille); j++)
			{
				image[i][j] = (!fatigue) ? 2 : 3;
			}
		}
		return image;
	}
}
