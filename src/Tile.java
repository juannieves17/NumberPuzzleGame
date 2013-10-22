
import java.awt.Rectangle;

/**
 * This class will construct ONE title of the game.
 * @author Juan Nieves y Luis Rivera
 *
 */
public class Tile 
{

	/** Construct a single tile in a specific position and width.
	 * @param x Sets the x-coordinate of the title
	 * @param y Sets the y-coordinate of the title
	 * @param titleNumber Number in the title
	 */
	public Tile(double x, double y,double width, int tileNumber) 
	{
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.tileNumber = tileNumber;

		//Construct the title with the Rectangle Class.
		tile = new Rectangle.Double(this.x, this.y, this.width, this.width); 
		this.numberPositionX = tile.getCenterX();
		this.numberPositionY = tile.getCenterY();
	}

	/**
	 * This method will get the number in the tile
	 * @return titleNumber
	 */
	public int getTileNumber() 
	{
		return tileNumber;
	}

	/**
	 * This method sets the number in the tile.
	 * @param titleNumber
	 */
	public void setTileNumber(int titleNumber) 
	{
		this.tileNumber = titleNumber;
	}

	/**
	 * The method sets the width of the tile.
	 * @param width
	 */
	public void setWidth(double width) 
	{
		this.width = width;
	}

	/**
	 * The method gets the width of the tile
	 * @param width
	 */
	public double getWidth() 
	{
		return this.width;
	}

	/** This method return the x-coordinate.
	 * @return the x
	 */
	public double getX() 
	{
		return x;
	}

	/** This method sets the x-coordinate
	 * @param x 
	 */
	public void setX(double x) 
	{
		this.x = x;
		tile.setRect(x, this.y, this.width, this.width);
	}

	/** This method return the y-coordinate.
	 * @return  y
	 */
	public double getY() 
	{
		return y;
	}

	/** This method sets the y-coordinate
	 * @param y 
	 */
	public void setY(double y) 
	{
		this.y = y;
		tile.setRect(this.x,y,this.width,this.width);
	}

	/** This method return the title.
	 * @return title
	 */
	public Rectangle.Double getTile() 
	{
		return tile;
	}


	/** Method that gets x-coordinate of the number in the tile.
	 * @return numberPositionX
	 */
	public double getNumberPositionX() 
	{
		return this.numberPositionX;
	}

	/** Method that sets x-coordinate of the number in the tile.
	 * @param numberPositionX the numberPositionX to set
	 */
	public void setNumberPositionX(double numberPositionX) 
	{
		this.numberPositionX = numberPositionX;
	}

	/** Method that gets the y-coordinate of the number in tile
	 * @return the numberPositionY
	 */
	public double getNumberPositionY() 
	{
		return this.numberPositionY;
	}

	/** Method that sets the y-coordinate of the number in tile
	 * @param numberPositionY the numberPositionY to set
	 */
	public void setNumberPositionY(double numberPositionY) 
	{
		this.numberPositionY = numberPositionY;
	}

	//Instance Fields
	private double numberPositionX;
	private double numberPositionY;
	private double x;
	private double y;
	private double width;
	private int tileNumber;
	private Rectangle.Double tile;

}
