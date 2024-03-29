/*
 * Name: Justin Senia
 * E-Number: E00851822
 * Date: 10/20/2017
 * Class: COSC 461
 * Project: #2
 */
import java.util.LinkedList;
import java.io.*;
import java.util.*;

public class TravelPruneTester
{

    //Main method for testing
    public static void main(String[] args) throws IOException
    {
      //creating buffered reader for getting user input
      java.io.BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));

	  //message welcoming to the program/giving instructions
      System.out.println("*****************************************");
      System.out.println("*      Travelling Salesman program      *");
      System.out.println("*       (Using Pruning Heuristic)       *");
      System.out.println("*****************************************");
      System.out.println("*  Please enter in a filename to start  *");
      System.out.println("* or type quit to terminate the program *");
      System.out.println("*****************************************");

      //start a loop that continues querying for input as long as user
      //does not enter "quit" (without the quotes)
      while (true)
      {
        System.out.print("Please make your entry now: ");
        String userIn = ""; //used for file entry and/or quitting

        userIn = keyIn.readLine(); //reading user input

        if (userIn.equalsIgnoreCase("quit")) //if user typed quit, stops program
          break;
        else{
              try
              {
				//establishing working directory for file I/O
                String currentDir = System.getProperty("user.dir");
                File fIn = new File(currentDir + '\\' + userIn);
				
				//using scanner with new input file based on user input
                Scanner scanIn = new Scanner(fIn);

				//creating printwriter for file output
                File fOut = new File("output_" + userIn);
                PrintWriter PWOut = new PrintWriter(fOut, "UTF-8");

				//scanning external file for Board dimensions & number of possible verts
                int numOfVerts = scanIn.nextInt();
                int numOfEdges = scanIn.nextInt();
                int bCols = 3;

				//declaring multidimensional array to hold externally read data
                int[][] edges = new int[numOfEdges][bCols];

                for (int i = 0; i < numOfEdges; i++)
                {
                  for (int j = 0; j < bCols; j++)
                  {
					if (j < 2)
					{
						edges[i][j] = scanIn.nextInt() - 1;		//decreasing vert name value by 1 for
						//System.out.print(" " + edges[i][j]);	//ease of array use
					}											//will increment again upon printing
					else if (j == 2)
					{
						edges[i][j] = scanIn.nextInt();			//not modifying distance data
						//System.out.print(" " + edges[i][j]);
					}
                  }
                  //System.out.println(""); //used to see if arrays transferred correctly
                }

				//printing labels so user will know it's output
                System.out.println("\n***TSP Pruning Output for " + userIn + "***\n");
                PWOut.println("\n***TSP Pruning Output for " + userIn + "***\n");

				//creating new TravelPrune object and calling timedSolve Method
                TravelPrune tP = new TravelPrune(numOfVerts, edges, PWOut);
                tP.timedSolve();

				//closing printwriter and scanner objects to maintain file integrity
                scanIn.close();
                PWOut.close();
              }
              catch (IOException e) //catches if there were any fileIO exceptions
              {
                ;
              }
            }
      }
    }
}
