


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * 
 * @author Juan Nieves y Luis Rivera
 *
 */
public class NPGMain
{

	/**This Class will create the Number Puzzle Game for 1 Player or 2 Players. It will have the options to the user enter the
	 * numbers in the order he desires to begin the game or the game will automatically do it randomly. Also when playing in two players 
	 * mode, the WASD keys are enable for the 2nd player and the Arrow Keys for the 1st player. The game will ask for Username and size 
	 * of matrix from 3 to 6. For last the game will only exit if an user press Alt+F4 or winning the game.
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{

		//Know the number of players.
		while(done == false)
		{
			numberPlayer = JOptionPane.showInputDialog("One player or Two Player (Press 1 for one player, and 2 for two player): ");
			if(numberPlayer == null)
			{
				System.exit(0);
			}
			if(numberPlayer.equals("1") || numberPlayer.equals("2") )
			{
				done = true;
			}

		}

		//Know the size of the matrix.
		while(done2 == false)
		{
			String size = JOptionPane.showInputDialog("Enter the size number (Example: 3 for a 3x3, 4x4, 5x5 or 6x6): ");
			if(size == null)
			{
				System.exit(0);
			}
			if(size.equals("3") || size.equals("4") || size.equals("5") || size.equals("6"))
			{
				matrixSize = Integer.parseInt(size);
				done2 = true;
			}
		}

		if(numberPlayer.equals("1"))	
		{	
			String name = JOptionPane.showInputDialog("Username: ");
			if(name == null)
			{
				System.exit(0);
			}
			//Know if you want to put the numbers yourself or not.
			while(done3 == false)
			{
				randomOrNot = JOptionPane.showInputDialog("Will you like to enter the order of the matrix (Yes or No)?: ");
				if(randomOrNot == null)
				{
					System.exit(0);
				}

				if(randomOrNot.equalsIgnoreCase("yes") || randomOrNot.equalsIgnoreCase("no"))
				{
					done3 = true;
				}
			}

			if(randomOrNot.equalsIgnoreCase("Yes"))
			{
				int matrixOrdered[][] = new int[matrixSize][matrixSize];
				Scanner in = new Scanner(System.in);
				JOptionPane.showMessageDialog(null,"Enter the numbers in the console, from 0 until size of Matrix n^2- 1: \n");
				System.out.println("The numbers in the console, from 0 until size of Matrix n^2- 1 ");
				System.out.println("Enter number (after every number press SPACE): ");

				//This for will put the numbers you enter in the console to a bidimentional array.
				ArrayList<Integer> numbersEntered = new ArrayList<Integer>();

				for(int i = 0; i< matrixSize ; i++)
				{
					for(int j = 0; j< matrixSize ; j++)
					{
						try{
							matrixOrdered[i][j] = in.nextInt();
							if(matrixOrdered[i][j] > (matrixSize * matrixSize - 1)
									|| matrixOrdered[i][j]<0 || numbersEntered.contains(matrixOrdered[i][j]))
							{
								System.out.println("The entered number is not valid or it already is in the list.");
								j--;
							}
							else{
								numbersEntered.add(matrixOrdered[i][j]);
							}

						}
						catch(InputMismatchException e)
						{
							System.out.println("Invalid character you should enter only numbers from 0 to " + (matrixSize * matrixSize - 1) + ". Try again");
							j--;
							in = new Scanner(System.in);
						}

					}
				}

				//This "if" and the "else if" will create the matrix from the size given by the user.
				if(matrixSize == 3)
				{
					matrix1 = new TilesRandomGenerator(matrixOrdered, matrixSize,TITLE_WIDTH3, tilesBegin,tilesBegin,0,0);
				}
				else if(matrixSize == 4)
				{
					matrix1 = new TilesRandomGenerator(matrixOrdered, matrixSize,TITLE_WIDTH4, tilesBegin,tilesBegin,0,0);
				}
				else if(matrixSize == 5)
				{
					matrix1 = new TilesRandomGenerator(matrixOrdered, matrixSize,TITLE_WIDTH5, tilesBegin,tilesBegin,0,0);
				}
				else if(matrixSize == 6)
				{
					matrix1 = new TilesRandomGenerator(matrixOrdered, matrixSize,TITLE_WIDTH6, tilesBegin,tilesBegin,0,0);
				}	
			}

			//This will generate the matrix  of the size given by user randomly.
			else 
			{		
				if(matrixSize == 3)
				{
					matrix1 = new TilesRandomGenerator(matrixSize,TITLE_WIDTH3, tilesBegin,tilesBegin, 0, 0);
				}
				else if(matrixSize == 4)
				{
					matrix1 = new TilesRandomGenerator(matrixSize,TITLE_WIDTH4, tilesBegin,tilesBegin, 0, 0);
				}
				else if(matrixSize == 5)
				{
					matrix1 = new TilesRandomGenerator(matrixSize,TITLE_WIDTH5, tilesBegin,tilesBegin, 0, 0);
				}
				else if(matrixSize == 6)
				{
					matrix1 = new TilesRandomGenerator(matrixSize,TITLE_WIDTH6, tilesBegin,tilesBegin, 0, 0);
				}
			}

			//Will construct the frame and add all the components in it.

			JFrame frame = new JFrame();
			frame.setSize(504,528);
			frame.setResizable(false);
			frame.setLocation(100, 50);
			frame.setTitle("Number Puzzle Game");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


			NPGComponent onePlayer = new NPGComponent(matrix1.getTitles(), matrixSize, name,0,0,1);
			frame.add(onePlayer);
			frame.addKeyListener(onePlayer);
			frame.setVisible(true);

		}//End big If

		//This will create the NPG for a two player.
		if(numberPlayer.equals("2"))
		{
			name = JOptionPane.showInputDialog("First player Username: ");
			if(name == null)
			{
				System.exit(0);
			}
			name2 = JOptionPane.showInputDialog("Second player Username: ");
			if(name2 == null)
			{
				System.exit(0);
			}

			String randomOrNot = JOptionPane.showInputDialog("Will you like to enter the order of the matrix (Yes or No)?: ");
			if(randomOrNot == null)
			{
				System.exit(0);
			}
			if(randomOrNot.equalsIgnoreCase("Yes"))
			{
				int matrixOrdered[][] = new int[matrixSize][matrixSize];
				Scanner in = new Scanner(System.in);

				JOptionPane.showMessageDialog(null,"Enter the numbers in the console, from 0 until size of Matrix - 1: \n");
				System.out.println("Enter number (after every number press ENTER): ");

				//This for will put the numbers you enter in the console to a bidimentional array
				//Read the ordered matrix.
				ArrayList<Integer> numbersEntered = new ArrayList<Integer>();
				for(int i = 0; i< matrixSize ; i++)
				{
					for(int j = 0; j< matrixSize ; j++)
					{
						try{
							matrixOrdered[i][j] = in.nextInt();
							if(matrixOrdered[i][j] > (matrixSize * matrixSize - 1)
									|| matrixOrdered[i][j]<0 || numbersEntered.contains(matrixOrdered[i][j]))
							{
								System.out.println("The entered number is not valid or it already is in the list.");
								j--;
							}
							else{
								numbersEntered.add(matrixOrdered[i][j]);
							}

						}
						catch(InputMismatchException e)
						{
							System.out.println("Invalid character you should enter only numbers from 0 to " + (matrixSize * matrixSize - 1) + ". Try again");
							j--;
							in = new Scanner(System.in);
						}
					}
				}

				//This will create the matrix given by user for the first player and another matrix given by user for second player.
				if(matrixSize == 3)
				{
					matrix1 = new TilesRandomGenerator(matrixOrdered, matrixSize,TITLE_WIDTH3, tilesBegin,tilesBegin,590, 40);
				}
				else if(matrixSize == 4)
				{
					matrix1 = new TilesRandomGenerator(matrixOrdered, matrixSize,TITLE_WIDTH4, tilesBegin,tilesBegin,590, 40);
				}
				else if(matrixSize == 5)
				{
					matrix1 = new TilesRandomGenerator(matrixOrdered, matrixSize,TITLE_WIDTH5, tilesBegin,tilesBegin,590, 40);
				}
				else if(matrixSize == 6)
				{
					matrix1 = new TilesRandomGenerator(matrixOrdered, matrixSize,TITLE_WIDTH6, tilesBegin, tilesBegin,590, 40);
				}	
			}

			//This will create one random matrix for the first player and the same random matrix for second player
			else
			{		
				//NUEVO!!!!
				if(matrixSize == 3)
				{
					matrix1 = new TilesRandomGenerator(matrixSize,TITLE_WIDTH3, tilesBegin, tilesBegin,590, 40);
				}
				else if(matrixSize == 4)
				{
					matrix1 = new TilesRandomGenerator(matrixSize,TITLE_WIDTH4, tilesBegin, tilesBegin,590, 40);

				}
				else if(matrixSize == 5)
				{
					matrix1 = new TilesRandomGenerator(matrixSize,TITLE_WIDTH5, tilesBegin, tilesBegin,590, 40);
				}
				else if(matrixSize == 6)
				{
					matrix1 = new TilesRandomGenerator(matrixSize,TITLE_WIDTH6, tilesBegin, tilesBegin,590, 40);
				}
			}

			//Will construct the frame and add all the components in it.
			JFrame frame = new JFrame();
			frame.setSize(1054,528);
			frame.setResizable(false);
			frame.setLocation(100,50);
			frame.setTitle("Number Puzzle Game");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			NPGComponent component = new NPGComponent(matrix1.getTitles(), matrixSize, name2,0,0,2);
			frame.add(component);
			frame.addKeyListener(component);
			frame.setVisible(true);

			NPGComponent component2 = new NPGComponent(matrix1.getTitles2(), matrixSize, name,550,0,1);
			frame.add(component2);
			frame.addKeyListener(component2);
			frame.setVisible(true);


		}//End of Big "else" for the two players game.		
	}//End of main

	//Instance Fields
	private static TilesRandomGenerator matrix1;
	private static int matrixSize;
	private static String numberPlayer;
	private static String name2;
	private static String name;
	private static String randomOrNot;
	private static boolean done = false;
	private static boolean done2 = false;
	private static boolean done3 = false;

	//Constants
	private  final static int tilesBegin = 40;
	private  final static int TITLE_WIDTH3 = 140;
	private  final static int TITLE_WIDTH4 = 105;
	private  final static int TITLE_WIDTH5 = 85;
	private  final static int TITLE_WIDTH6 = 70;

}//End of Program
