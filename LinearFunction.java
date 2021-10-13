/*
*	Crist, R.M., Drumheller, G.L.
* 	CSE 143
* 
* 	The LinearFunction class is a class that creates a new linear function  
*		based data passed in by the user and graphs it onto whatever graph 
* 		the user would like 
*	
*/

import java.awt.*;
import java.util.*;


public class LinearFunction{
	private int slope;
	private int yIntercept;
	
	private int graphWidth;
	private int graphHeight;
	
	private Color color;
	
	private Random r;
	
	private Graphics g;
	
	private final int SCALE = 100;

	private Map<Integer, Integer> points;
	
	// Constructor of the class that initializes all fields
	// Receives int slope and int yIntercept as parameters
	// 		what function the user is trying to graph
	// Receives graphics g so that the function can be graphed onto the 
	// 		the specified drawing panel
	// Receives int graphWidth and int graphHeight so that the function 
	// 		knows the dimensions of the panel that it is being graphed onto
	public LinearFunction(int slope, int yIntercept, Graphics g, int graphWidth, int graphHeight) {
		this.slope = slope;
		this.yIntercept = yIntercept;
		
		r = new Random();
		
		color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		
		points = new HashMap<Integer, Integer>();
		
		this.graphWidth = graphWidth;
		this.graphHeight = graphHeight;
		
		this.g = g;
		
		
	}
	
	// Draws a linear function onto the specified drawing panel
	public void draw() {
		
		int x1 = (graphWidth / 2);
		int y1 = ((graphHeight / 2) - SCALE * yIntercept);
		
		int x2 = (graphWidth / 2);
		int y2 = ((graphHeight / 2) - SCALE * yIntercept);
		
		int i = 0;
		
		while(x1 <= graphWidth && y1 <= graphHeight && y1 >= 0){
			x1 = i + (graphWidth / 2);
			y1 = (slope * -i) + ((graphHeight / 2) - SCALE * yIntercept);
				
			points.put(x1, y1);
			//System.out.println(x1 + " " + y1);
			
			i++;
		}
		
		i = 0;
		
		while(x2 >= 0 && y2 <= graphHeight && y2 >= 0){
			x2 = i + (graphWidth / 2);
			y2 = (slope * -i) + ((graphHeight / 2) - SCALE * yIntercept);
				
			points.put(x2, y2);

			i--;
		}
		
		g.setColor(color);
		g.drawLine(x1,y1,x2,y2);
	}
	
	// Returns a map of the points of the function that are on the graph
	// There is one point per pixel 
	public Map<Integer, Integer> getPoints(){
		return points;
	}
}
