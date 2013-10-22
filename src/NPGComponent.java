
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.*;
import javax.sound.sampled.*;
import javax.swing.*;

import java.io.*;

/**
 * This class will create all the components for the interface of Number Puzzle Game for both players. 
 * It will create the background, the matrix of tiles, player's string name, tiles movement, music, 
 * and all the colors in tiles,numbers and game board.
 * 
 * @author Juan Nieves y Luis Rivera
 *
 */
public class NPGComponent extends JComponent implements ActionListener, KeyListener 
{

	/**
	 * Construct the game complete interface.
	 * 
	 * @param matrixTitle Receive the bidimentional matrix
	 * @param matrixSize  The matrix size ( example: 3x3,4x4,...)
	 * @param playerName  the username of the player.
	 * @param x  Sets the x-coordinate of the position of the matrix
	 * @param y  Sets the y-coordinate of the position of the matrix
	 */
	public NPGComponent(Tile[][] matrixTitle, int matrixSize, String playerName,double x, double y,int player) 
	{
		super();
		this.matrixTitle = matrixTitle;
		this.matrixSize = matrixSize;
		this.playerName = playerName;
		this.x1 = x;
		this.y1 = y;
		this.player = player;
		t = new Timer(10, this);

		if (this.player == 1)
		{	music();//Start the music when the game begin.

		//Set the keys for the user.
		UP = KeyEvent.VK_UP;
		DOWN = KeyEvent.VK_DOWN;
		RIGHT = KeyEvent.VK_RIGHT;
		LEFT = KeyEvent.VK_LEFT;

		}
		else if (this.player ==2)
		{
			UP = KeyEvent.VK_W;
			DOWN = KeyEvent.VK_S;
			RIGHT = KeyEvent.VK_D;
			LEFT = KeyEvent.VK_A;
		}



	}

	/**
	 * This will create all the graphics for the game.
	 * Backgrounds, color the features, music and the movement of the tiles.
	 *
	 */
	public void paintComponent(Graphics g)
	{

		Graphics2D g2 = (Graphics2D) g; //Recovers Graphics2D
		Color lightBlue = new Color(39,64,139);//Inner background
		Color darkBlue = new Color(0,0,128);//Outer Background

		//This will draw, paint and fill the outer bigger rectangle for the frame of the game.
		Rectangle.Double r1 = new Rectangle.Double(this.x1,this.y1,500,500);//Defines the rectangles
		g2.draw(r1);
		g2.setColor(Color.BLUE);
		g2.fill(r1);


		//This will draw, paint and fill the black outer border and the the light color rectangle.
		Rectangle.Double borderOut = new Rectangle.Double(this.x1 + 40,this.y1 + 40,420,420);
		g2.setColor(Color.BLACK);
		g2.draw(borderOut);


		Rectangle.Double rectOut = new Rectangle.Double(this.x1 +42,this.y1 +42,417,417);
		g2.setColor(lightBlue);
		g2.draw(rectOut);
		g2.fill(rectOut);

		//This will draw, paint and fill the black inner border and the inner darker color rectangle.
		Rectangle.Double borderIn = new Rectangle.Double(this.x1 +60,this.y1 +60,380,380);
		g2.setColor(Color.BLACK);
		g2.draw(borderIn);

		Rectangle.Double rectIn = new Rectangle.Double(this.x1 +62,this.y1 +62,377,377);
		g2.setColor(darkBlue);
		g2.draw(rectIn);
		g2.fill(rectIn);


		//Draw the Exit Game string to how to get out of the game.
		g2.setColor(Color.WHITE);
		g2.drawString("Exit Game: Alt + F4",(int)this.x1 + 360, 35);

		//Draw the name of the player.
		Font italic = new Font(playerName, Font.ITALIC, 20);
		g2.setFont(italic);
		g2.drawString(playerName,(int)this.x1 +40,(int)this.y1 +35);


		//This two for will draw, paint and fill the title inside the grid except when the number is 0.
		for(int i = 0; i < matrixSize ; i++)
		{
			for(int j = 0; j< matrixSize ; j++)
			{
				//If the matrix if 0 will not paint nothing.
				if(matrixTitle[i][j].getTileNumber() == 0)
				{
					this.i = i;
					this.j = j;
				}
				else
				{	
					//Draw the matrix and fill it
					g2.draw(matrixTitle[i][j].getTile());
					g2.setColor(Color.WHITE);
					g2.fill(matrixTitle[i][j].getTile());

					//Draw the grid of the matrix
					g2.setColor(Color.BLACK);
					g2.draw(matrixTitle[i][j].getTile());

					//Draw and paint the numbers in the grid of the matrix.
					//Also will paint the number Red if they are Even and Blue if they are Odd.
					if(matrixSize == 3)
					{
						//Change the font and size of the numbers in the matrix of numbers in the grid.
						Font bold = new Font(playerName, Font.BOLD,60);
						g2.setFont(bold);
						if(matrixTitle[i][j].getTileNumber()%2 == 0)
						{
							g2.setColor(Color.RED);
							g2.drawString(String.valueOf(matrixTitle[i][j].getTileNumber()),(int) matrixTitle[i][j].getNumberPositionX()-20,
									(int) matrixTitle[i][j].getNumberPositionY()+20);
						}
						else
						{
							g2.setColor(Color.BLUE);
							g2.drawString(String.valueOf(matrixTitle[i][j].getTileNumber()),(int) matrixTitle[i][j].getNumberPositionX()-20,
									(int) matrixTitle[i][j].getNumberPositionY()+20);
						}
					}
					if(matrixSize == 4)
					{
						//Change the font and size of the numbers in the matrix of numbers in the grid.
						Font bold = new Font(playerName, Font.BOLD,50);
						g2.setFont(bold);
						if(matrixTitle[i][j].getTileNumber()%2 == 0)
						{
							g2.setColor(Color.RED);
							g2.drawString(String.valueOf(matrixTitle[i][j].getTileNumber()),(int) matrixTitle[i][j].getNumberPositionX()-20,
									(int) matrixTitle[i][j].getNumberPositionY()+20);
						}
						else
						{
							g2.setColor(Color.BLUE);
							g2.drawString(String.valueOf(matrixTitle[i][j].getTileNumber()),(int) matrixTitle[i][j].getNumberPositionX()-20,
									(int) matrixTitle[i][j].getNumberPositionY()+20);
						}
					}
					if(matrixSize == 5)
					{
						//Change the font and size of the numbers in the matrix of numbers in the grid.
						Font bold = new Font(playerName, Font.BOLD,40);
						g2.setFont(bold);
						if(matrixTitle[i][j].getTileNumber()%2 == 0)
						{
							g2.setColor(Color.RED);
							g2.drawString(String.valueOf(matrixTitle[i][j].getTileNumber()),(int) matrixTitle[i][j].getNumberPositionX()-20,
									(int) matrixTitle[i][j].getNumberPositionY()+20);
						}
						else
						{
							g2.setColor(Color.BLUE);
							g2.drawString(String.valueOf(matrixTitle[i][j].getTileNumber()),(int) matrixTitle[i][j].getNumberPositionX()-20,
									(int) matrixTitle[i][j].getNumberPositionY()+20);
						}
					}
					if(matrixSize == 6)
					{
						//Change the font and size of the numbers in the matrix of numbers in the grid.
						Font bold = new Font(playerName, Font.BOLD,40);
						g2.setFont(bold);
						if(matrixTitle[i][j].getTileNumber()%2 == 0)
						{
							g2.setColor(Color.RED);
							g2.drawString(String.valueOf(matrixTitle[i][j].getTileNumber()),(int) matrixTitle[i][j].getNumberPositionX()-20,
									(int) matrixTitle[i][j].getNumberPositionY()+20);
						}
						else
						{
							g2.setColor(Color.BLUE);
							g2.drawString(String.valueOf(matrixTitle[i][j].getTileNumber()),(int) matrixTitle[i][j].getNumberPositionX()-20,
									(int) matrixTitle[i][j].getNumberPositionY()+20);
						}
					}
				}
			}//End of inner for
		}//End of outer For

	}//End of paintComponent

	@Override
	public void keyPressed(KeyEvent e) 
	{	
		//*****loop that runs every time to check if the user win the game.
		int k = 1;
		for(int i = 0; i< matrixSize; i++)
		{
			for(int j = 0; j< matrixSize; j++)
			{

				if(k == matrixSize*matrixSize && matrixTitle[i][j].getTileNumber() == 0)
				{
					win = true;
					break;

				}
				if(matrixTitle[i][j].getTileNumber() != k)
				{
					win = false;
					break;	
				}
				k++;
			}
		}

		if (win == true)
		{
			//NUEVO *** Find out what is UIManager.
			Color opanel = new Color(30,144,255);
			try {
				imageLocation = new URL("http://arcaraz.com/blog/wp-content/uploads/2011/06/chewbacca.jpg");
			} catch (MalformedURLException e1) {
				e1.printStackTrace();

			}	
			UIManager.put("OptionPane.background",opanel);
			JOptionPane.showMessageDialog(null,"Congratulation!!! "+this.playerName+" You have WON the Game","Number Puzzle Game", JOptionPane.PLAIN_MESSAGE, new ImageIcon(imageLocation));
			System.exit(0);
		}
		else
		{
			if(t.isRunning() == false)
			{
				keyword = e.getKeyCode();

				this.swap();
			}
		}

		//To get out of the game
		if(e.getKeyCode() == (KeyEvent.VK_ALT + KeyEvent.VK_F4))
		{
			System.exit(0);
		}

	}

	/**
	 * This method will add the music to the game.
	 */
	public static void music()
	{
		try {
			// from a wave File
			soundFile = new File("starWars.wav");
			audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();

			// Open audio clip, start it and loop it many times.
			clip.open(audioIn);
			clip.start();
			clip.loop(20);

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

	}
	/**
	 * This method will add the sound effect to the keys
	 */
	public static void soundEffect()
	{
		try {
			// from a wave File
			soundFile = new File("Laser.wav");
			audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();

			// Open audio clip, start it and loop it many times.
			clip.open(audioIn);
			clip.start();

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

	}


	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	/**
	 * This Method will enter when an action is performed.
	 * @param e This is an Action Event
	 */
	public void actionPerformed(ActionEvent e) 
	{
		//if 0 left title right
		if(matrixTitle[in][jn].getX() > matrixTitle[this.i][this.j].getX())
		{
			dx = -matrixTitle[0][0].getWidth()/20.0;
			dy = 0;

			matrixTitle[in][jn].setX(matrixTitle[in][jn].getX()+dx);
			matrixTitle[in][jn].setY(matrixTitle[in][jn].getY()+dy);
			matrixTitle[in][jn].setNumberPositionX(matrixTitle[in][jn].getNumberPositionX()+dx);
			matrixTitle[in][jn].setNumberPositionY(matrixTitle[in][jn].getNumberPositionY()+dy);
			repaint();
		}
		//if title left 0 right
		if(matrixTitle[in][jn].getX() < matrixTitle[this.i][this.j].getX())
		{
			dx = matrixTitle[0][0].getWidth()/20.0;
			dy = 0;

			matrixTitle[in][jn].setX(matrixTitle[in][jn].getX()+dx);
			matrixTitle[in][jn].setY(matrixTitle[in][jn].getY()+dy);
			matrixTitle[in][jn].setNumberPositionX(matrixTitle[in][jn].getNumberPositionX()+dx);
			matrixTitle[in][jn].setNumberPositionY(matrixTitle[in][jn].getNumberPositionY()+dy);
			repaint();
		}
		//if title down 0 up
		if(matrixTitle[in][jn].getY() > matrixTitle[this.i][this.j].getY())
		{
			dy = -matrixTitle[0][0].getWidth()/20.0;;
			dx = 0;

			matrixTitle[in][jn].setX(matrixTitle[in][jn].getX()+dx);
			matrixTitle[in][jn].setY(matrixTitle[in][jn].getY()+dy);
			matrixTitle[in][jn].setNumberPositionX(matrixTitle[in][jn].getNumberPositionX()+dx);
			matrixTitle[in][jn].setNumberPositionY(matrixTitle[in][jn].getNumberPositionY()+dy);
			repaint();
		}
		//if 0 down title up
		if(matrixTitle[in][jn].getY() < matrixTitle[this.i][this.j].getY())
		{
			dy = matrixTitle[0][0].getWidth()/20.0;
			dx = 0;

			matrixTitle[in][jn].setX(matrixTitle[in][jn].getX()+dx);
			matrixTitle[in][jn].setY(matrixTitle[in][jn].getY()+dy);
			matrixTitle[in][jn].setNumberPositionX(matrixTitle[in][jn].getNumberPositionX()+dx);
			matrixTitle[in][jn].setNumberPositionY(matrixTitle[in][jn].getNumberPositionY()+dy);
			repaint();
		}

		//When the tile moving will stop.
		if((matrixTitle[in][jn].getY() == matrixTitle[this.i][this.j].getY())&&(matrixTitle[in][jn].getX() == matrixTitle[this.i][this.j].getX()))
		{
			t.stop();
			matrixTitle[this.i][this.j].setY(tempy);
			matrixTitle[this.i][this.j].setX(tempx);

			Tile temp = matrixTitle[in][jn];
			matrixTitle[in][jn] = matrixTitle[i][j];
			matrixTitle[i][j] = temp;
			i = in;
			j = jn;
		}


	}//End of actionPerformed method


	/**
	 * This method swaps the tile with the space tile.
	 */
	private void swap()
	{
		in = -1;
		jn = -1;
		if (UP == keyword && i < matrixSize-1)
		{
			in = this.i + 1;
			jn = this.j;
		}
		else if (DOWN == keyword && i > 0)
		{
			in = this.i -1;
			jn = this.j;
		}
		else if (LEFT == keyword && j < matrixSize-1)
		{
			in = this.i;
			jn = this.j +1;
		}
		else if (RIGHT == keyword && j > 0)
		{
			in = this.i;
			jn = this.j -1;
		}
		if(in != -1 && jn != -1)
		{ 
			tempx = matrixTitle[in][jn].getX();
			tempy = matrixTitle[in][jn].getY();
			soundEffect();
			t.start();
		}	
	}

	//keyboard keys
	private  int UP;
	private  int DOWN;
	private  int RIGHT;
	private  int LEFT;

	//Temp's for storing values in them.
	private double tempx;
	private double tempy;

	//Translate differential
	private double dx;
	private double dy;

	//Audio Fields
	private static File soundFile;
	private static Clip clip;
	private static AudioInputStream audioIn;


	//Swapping title array position
	private int in = -1;
	private int jn = -1;


	//Instance Fields
	private Timer t;
	private int player;
	private int keyword;
	private boolean win;
	private URL imageLocation;
	private int i;
	private int j;
	private Tile[][] matrixTitle;
	private  int matrixSize;
	private String playerName;
	private double x1;
	private double y1;
	private static final long serialVersionUID = 1L;	
}

