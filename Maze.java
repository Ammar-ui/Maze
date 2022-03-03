
/******************************************************************************
 *  @(#)Maze.java
 *
 *  Generates an n-by-n maze.
 *
 *	Compilation:  javac Maze.java
 *  Execution:    java Maze.java n
 *  Dependencies: Std2Draw.java
 *
 *	@author Dr. Abdulghani M. Al-Qasimi
 *  @version 3.00 2021/9/28
 *
 ******************************************************************************/

/******************************************************************************
 *                  I M P O R T A N T
 *
 * Do not modify any thing after this line until instructed below
 *
 ******************************************************************************/
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import java.util.Stack;

//import org.w3c.dom.Node;

public class Maze {
    private int n;                  // dimension of maze
    private boolean[][] north;      // is there a wall to north of cell i, j
    private boolean[][] east;       // is there a wall to east of cell i, j
    private boolean[][] south;      // is there a wall to south of cell i, j
    private boolean[][] west;       // is there a wall to west of cell i, j
     static boolean[][] visited;    // flag a visited cell
    private boolean done = false;   // general flag
	private Random rand;            // random number generator
	
	// The maze constructor
	// generate a random maze and displays it
    public Maze(int n) {
        this.n = n;
    	rand = new Random();

		// Initialize sequence from system current time
    	rand.setSeed(System.currentTimeMillis());

        Std2Draw.setXscale(0, n+2);
        Std2Draw.setYscale(0, n+2);
        init();
        generate();
    }

    private void init() {
        // initialize border cells as already visited
        visited = new boolean[n+2][n+2];
        for (int x = 0; x < n+2; x++) {
            visited[x][0] = true;
            visited[x][n+1] = true;
        }
        for (int y = 0; y < n+2; y++) {
            visited[0][y] = true;
            visited[n+1][y] = true;
        }


        // initialze all walls as present
        north = new boolean[n+2][n+2];
        east  = new boolean[n+2][n+2];
        south = new boolean[n+2][n+2];
        west  = new boolean[n+2][n+2];
        for (int x = 0; x < n+2; x++) {
            for (int y = 0; y < n+2; y++) {
                north[x][y] = true;
                east[x][y]  = true;
                south[x][y] = true;
                west[x][y]  = true;
            }
        }
    }


    // generate the maze
    
    private void generate(int x, int y) {

        visited[x][y] = true;

        // while there is an unvisited neighbor
        while (!visited[x][y+1] || !visited[x+1][y] || !visited[x][y-1] || !visited[x-1][y]) {

            // pick random neighbor
            while (true) {
                double r = rand.nextInt(4);
                if (r == 0 && !visited[x][y+1]) {
                    north[x][y] = false;
                    south[x][y+1] = false;
                    generate(x, y+1);
                    break;
                }
                else if (r == 1 && !visited[x+1][y]) {
                    east[x][y] = false;
                    west[x+1][y] = false;
                    generate(x+1, y);
                    break;
                }
                else if (r == 2 && !visited[x][y-1]) {
                    south[x][y] = false;
                    north[x][y-1] = false;
                    generate(x, y-1);
                    break;
                }
                else if (r == 3 && !visited[x-1][y]) {
                    west[x][y] = false;
                    east[x-1][y] = false;
                    generate(x-1, y);
                    break;
                }
            }
        }
        
    }
    
    // generate the maze starting from lower left
   
    private void generate() {
        generate(1, 1);
    }

    // draw the maze
    public void draw() {
        Std2Draw.setPenColor(Std2Draw.RED);
        Std2Draw.filledCircle(n/2.0+0.5, n/2.0+0.5, 0.375);
        Std2Draw.filledCircle(1.5, 1.5, 0.375);

        Std2Draw.setPenColor(Std2Draw.BLACK);
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                if (south[x][y]) Std2Draw.line(x, y, x+1, y);
                if (north[x][y]) Std2Draw.line(x, y+1, x+1, y+1);
                if (west[x][y])  Std2Draw.line(x, y, x, y+1);
                if (east[x][y])  Std2Draw.line(x+1, y, x+1, y+1);
            }
        }
        Std2Draw.show();
        Std2Draw.pause(1000);
    }

	// draw a blue dot in the right visited cell
	private void markCell(int x, int y) {
        Std2Draw.setPenColor(Std2Draw.BLUE);
        Std2Draw.filledCircle(x+0.5, y+0.5, 0.2);
        Std2Draw.show();
        Std2Draw.pause(30);
	}

	// draw a blue circle in the wrong visited cell
	private void unmarkCell(int x, int y) {
        Std2Draw.setPenColor(Std2Draw.WHITE);
        Std2Draw.filledCircle(x+0.5, y+0.5, 0.2);
        Std2Draw.show();
        Std2Draw.pause(30);
	}

    // a main method to test the program
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Maze maze = new Maze(n);
        Std2Draw.enableDoubleBuffering();
        maze.draw();
      //  maze.solve(visited);
        maze.solve();
    }



    // solve the maze using a stack
    // mark the right cell using markCell method
    // mark the wrong cell using unmarkCell method
    public void solve() {
    	
    	
    	for(int x=1;x <= n;x++)
    		for(int y=1;y<=n; y++)
               visited[x][y]=false;
    	done=false;
    	Operation(1 ,1);
    

	/************************************************************************
	*                           I M P O R T A N T
	*
	*  Start your modifications here by writing your own solve method
	*  using the stack ADT for backtracking as described in the assignment.
	*
	*************************************************************************/
    }
   
    public boolean Operation(int x ,int y) {
    	StackArray MyStack=new StackArray(n*n);// Creat an Object from my own StackArray Class 
    	//ArrayStack<int> n=new ArrayStack<int>();
    	//ArrayDeque v =new ArrayDeque ();
    	//Stack<int[][]> solve=new Stack<int[][]>();
    	///Stack<Node> s = new Stack<Node>();
    	//Node temp = new Node(x,y);
    	// it will work like loop and checker to reach  correct path  
    	if(x==0|| y==0 || x==n+1||y==n+1) {
    		
    		return done;
    		}   		    	    	
    	if(done ||visited [x][y])return done;
    	visited [x][y]=true;  // that already visited 
    	MyStack.push(new int[]{x, y}); // insert the values of X and Y in Array Stack 
   
    	markCell( x,  y); // draw for correct path
   
    	 if(x==n/2 && y==n/2)done =true; // condition of if it reach to exact point
    	 
        // if it true that mean there is a wall in north then will pop it then back to change path 
         if (!north[x][y]) {       	
        	 Operation(x, y+1);  
        	
        }
      // if it true that mean there is a wall in east then will pop it then back to change path 
         if (!east[x][y]) {         	
        	 Operation(x+1, y);  
        	
        }
      // if it true that mean there is a wall in south then will pop it then back to change path 
          if (!south[x][y]) {       
        	  Operation(x, y-1); 
        	
        }
       // if it true that mean there is a wall in west then will pop it then back to change path 
         if (!west[x][y]) {        	
        	 Operation(x-1, y);       	
        }
      
     MyStack.pop();
        if(done)return done;
        // draw wrong path
        unmarkCell(x,  y);
        
 return done;
    }
   
}