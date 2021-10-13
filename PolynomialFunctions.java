/*
*	Crist, R.M., Drumheller, G.L.
* 	CSE 143
* 
* 	The PolyNomialFunctions class is a class that creates a new polynomial function  
*		based data passed in by the user and graphs it onto whatever graph 
* 		the user would like 
*	
*/
import java.awt.*;
import java.util.*;



public class PolynomialFunctions {
	private int slopeOffset;
	private int horizontalShift;
	private int verticalShift;
	
	private int graphWidth;
	private int graphHeight;
	
	private Color color;
	
	private Random r;
	
	private Graphics g;
	
	private final int SCALE = 100;
	
	private Map<Integer, Integer> points;

	// Constructor of the class that initializes all fields
	// Receives int slopeOffset, int horizontalShift, and int verticalShift
	// 		 as parameters so it knows  what function the user is trying to graph
	// Receives graphics g so that the function can be graphed onto the 
	// 		the specified drawing panel
	// Receives int graphWidth and int graphHeight so that the function 
	// 		knows the dimensions of the panel that it is being graphed onto
	public PolynomialFunctions(int slopeOffset, int horizontalShift, int verticalShift, Graphics g, int graphWidth, int graphHeight) {
		this.slopeOffset = slopeOffset;
		this.horizontalShift = horizontalShift;
		this.verticalShift = verticalShift;
		
		r = new Random();
		
		color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		
		this.graphWidth = graphWidth;
		this.graphHeight = graphHeight;
		
		points = new HashMap<Integer, Integer>();
		
		this.g = g;
	}

	// Draws a polynomial function onto the specified drawing panel
	public void draw() {
		g.setColor(color);
		
		int x1 = (graphWidth / 2);
		int y1 = (graphHeight / 2);
		
		int x2 = (graphWidth / 2);
		int y2 = (graphHeight / 2);
		
		int i = 0;
		
		while(x2 <= graphWidth && y2 <= graphHeight && y2 >= 0){
			x1 = i + ((graphWidth / 2) - (horizontalShift * SCALE));
			y1 = (-slopeOffset * (i * i) / SCALE) + (graphHeight / 2 - (verticalShift * SCALE));
			
			x2 = (i + 1) + ((graphWidth / 2) - (horizontalShift * SCALE));
			y2 = (-slopeOffset * (i + 1) * (i + 1)) / SCALE + (graphHeight / 2 - (verticalShift * SCALE));
				
			g.drawLine(x1,y1,x2,y2);
			
			
			points.put(x1, y1);
			i++;
		}

		x1 = (graphWidth / 2);
		y1 = (graphHeight / 2);
		
		x2 = (graphWidth / 2);
		y2 = (graphHeight / 2);
		
		i = 0;

		while(x2 <= graphWidth && y2 <= graphHeight && y2 >= 0){
			x1 = i + ((graphWidth / 2) - (horizontalShift * SCALE));
			y1 = (-slopeOffset * (i * i) / SCALE) + (graphHeight / 2 - (verticalShift * SCALE));
			
			x2 = (i + 1) + ((graphWidth / 2) - (horizontalShift * SCALE));
			y2 = (-slopeOffset * (i + 1) * (i + 1)) / SCALE + (graphHeight / 2 - (verticalShift * SCALE));
				
			g.drawLine(x1,y1,x2,y2);
			
			
			points.put(x1, y1);
			i--;
		}
		
		
	}
	
	// Returns a map of the points of the function that are on the graph
	// There is one point per pixel 
	public Map<Integer, Integer> getPoints(){
		return points;
	}

}
