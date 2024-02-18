package glaces;
import glaces.Ocean;
import java.util.Random;


/**
 * Un poisson
 * @author Denys SHCHERBA
 */
public class Poisson
{
	private int x;
	private int y;
	private int largeur;
	private int hauteur;
	private int direction;
	private boolean deplacement;  // 1 for vertical, 0 for horizontal'
	private boolean isAlive;
	private int returnCount;  // nombre des retours
	
	public Poisson(int largeur, int hauteur, Ocean ocean)
	{
		Random g = new Random();
	
		this.isAlive = true;
		this.deplacement = g.nextBoolean();
		if (deplacement)
		{
		this.largeur = largeur;
		this.hauteur = hauteur;
		} else {
		this.largeur = hauteur;
		this.hauteur = largeur;
		}
		this.x = g.nextInt(ocean.getWidth() - this.largeur);
		this.y = g.nextInt(ocean.getHeight() - this.hauteur);
		this.direction = 1;
		this.returnCount = 0;
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return largeur;}
	public int getHeight() {return hauteur;}
	public boolean getIsAlive() {return isAlive;}
	public int getReturnCount() {return returnCount;}
	
	public boolean interactionField(int dist, int[][] field)
	{
		if (deplacement)
		{
			if (y+hauteur+(dist*direction)+1 >= field[0].length)
			{
				y = field[0].length - hauteur - 1;
				direction *= -1;
				returnCount += 1;
				return true;
			}
			else if (y+(dist*direction) < 0)
			{
				y = 0;
				direction *= -1;
				returnCount += 1;
				return true;
			}
		}
		else
		{
			if (x+largeur+(dist*direction)+1 >= field.length)
			{
				x = field.length - largeur - 1;
				direction *= -1;
				returnCount += 1;
				return true;
			}
			else if (x+(dist*direction) < 0)
			{
				x = 0;
				direction *= -1;
				returnCount += 1;
				return true;
			}
		}
		return false;
	}
	
	public void move(int dist, int[][] field)
	{
		if (!interactionField(dist, field))
		{
			if (deplacement)
			{
				this.y += dist * direction;
			}
			else
			{
				this.x += dist * direction;
			}
		}
	}
	
	public void kill()
	{
		isAlive = false;
	}
	
	public int[][] getColors(int[][] img)
	{
		int[][] image = img;
		for (int i = this.x; i < (this.x + this.largeur); i++)
		{
			for (int j = this.y; j < (this.y + this.hauteur); j++)
			{
				image[i][j] = (deplacement) ? 5 : 4;
			}
		}
		return image;
	}
}
