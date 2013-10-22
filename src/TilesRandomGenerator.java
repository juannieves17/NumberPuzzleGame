import java.util.Random;


/**
 * This class will generate a random matrix or a ordered matrix given by the user. Also it will
 * randomize one matrix or two matrix in cas if are two players playing.
 * @author Juan Nieves y Luis Rivera
 *
 */
public class TilesRandomGenerator 
{
	/**
	 * Construct the bidimentional matrix with the tiles and randomize them.
	 * Also construct the second matrix for the second player as the same as the first player matrix.
	 * 
	 * @param matrixSize Sets the matrix size.
	 * @param titlesWidth The width of the tile
	 * @param PPositionx  X-coordinate for the first player matrix
	 * @param PPositiony  Y-coordinate for the first player matrix
	 * @param positionX  x-coordinate for the second player matrix
	 * @param positionY  y-coordinate for the second player matrix
	 */
	public TilesRandomGenerator(int matrixSize, double tilesWidth,double PPositionx, double PPositiony,double positionX, double positionY)
	{
		this.PPositionx = PPositionx;
		this.PPositiony = PPositiony;
		this.positionX = positionX;
		this.positionY = positionY;
		tiles = new Tile[matrixSize][matrixSize];
		copyTile = new Tile[matrixSize][matrixSize];
		this.matrixSize = matrixSize;
		this.tilesWidth = tilesWidth;
		this.generateMatrix();
		this.randomize();

	}


	/**
	 * Construct the tile matrix with the matrix given by the user and generate it in the order he desire.
	 * Also construct the second matrix for the second player as the same as the first player matrix.
	 * 
	 * @param matrix  Sets the matrix from the user choice
	 * @param matrixSize Sets the matrix size.
	 * @param titlesWidth The width of the tile
	 * @param PPositionx  X-coordinate for the first player matrix
	 * @param PPositiony  Y-coordinate for the first player matrix
	 * @param positionX  x-coordinate for the second player matrix
	 * @param positionY  y-coordinate for the second player matrix
	 */
	public TilesRandomGenerator(int matrix[][], int matrixSize, double titlesWidth,double PPositionx, double PPositiony,double positionX, double positionY)
	{
		this.PPositionx = PPositionx;
		this.PPositiony = PPositiony;
		this.positionX = positionX;
		this.positionY = positionY;
		tiles = new Tile[matrixSize][matrixSize];
		copyTile = new Tile[matrixSize][matrixSize];
		this.matrixSize = matrixSize;
		this.tilesWidth = titlesWidth;
		this.generateOrderedMatrix(matrix);
	}

	/**
	 * This method will generated an Ordered Matrix from the users choice.
	 * Also creates the second EQUAL matrix for the second player.
	 * 
	 * @param matrix Receive the user's input matrix order.
	 */
	public void generateOrderedMatrix(int matrix[][])
	{
		for (int i = 0; i< matrixSize; i++)
		{
			for(int j = 0; j< matrixSize; j++)
			{
				tiles[i][j] = new Tile(PPositionx+tilesWidth*(j), PPositiony+tilesWidth*(i), tilesWidth, matrix[i][j]);

				//Nuevo For second player
				copyTile[i][j] = new Tile(positionX+tilesWidth*(j), positionY+tilesWidth*(i), tilesWidth, matrix[i][j]);
			}
		}
	}

	/**
	 * Method that generate a Random matrixes for first and second player.
	 * The matrix for both players is the same.
	 */
	public void generateMatrix()
	{

		for (int i = 0; i< matrixSize; i++)
		{
			for(int j = 0; j< matrixSize; j++)
			{
				tiles[i][j] = new Tile(PPositionx+tilesWidth*(j),PPositiony+tilesWidth*(i), tilesWidth,i*matrixSize+j);

				//NUEVO for second player
				copyTile[i][j]= new Tile(positionX+tilesWidth*(j), positionY+tilesWidth*(i), tilesWidth, i*matrixSize+j);

			}
		}
	}

	/**
	 * This method randomize the matrix of tiles.
	 * Create a second matrix exactly like the first randomize one.
	 */
	public void randomize()
	{
		for (int i = 0; i< matrixSize; i++)
		{
			for (int j = 0; j< matrixSize; j++)
			{

				int ri = generator.nextInt(matrixSize);
				int rj = generator.nextInt(matrixSize);
				int tempNum = tiles[i][j].getTileNumber();
				tiles[i][j].setTileNumber(tiles[ri][rj].getTileNumber());
				tiles[ri][rj].setTileNumber(tempNum);

				//NUEVO
				int tempNum2 = copyTile[i][j].getTileNumber();
				copyTile[i][j].setTileNumber(copyTile[ri][rj].getTileNumber());
				copyTile[ri][rj].setTileNumber(tempNum2);
			}
		}

	}

	/**
	 * Method that gets the first tile matrix
	 * @return titles
	 */
	public Tile[][] getTitles() 
	{

		return tiles;
	}
	/** NUEVO****
	 * Method that gets the second tile matrix
	 * @return titles
	 */
	public Tile[][] getTitles2() 
	{
		return copyTile;
	}


	//Instance Fields
	private final double PPositionx;
	private final double PPositiony;
	private double positionX;
	private double positionY;

	private int matrixSize;
	private double tilesWidth;
	private Random generator = new Random();
	private Tile[][] tiles;
	private Tile[][] copyTile;
}
