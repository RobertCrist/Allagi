/*
*	Crist, R.M., Drumheller, G.L.
* 	CSE 143
* 
* 	The LogFunction class is a class that creates a new logarithmic function  
*		based data passed in by the user and graphs it onto whatever graph 
* 		the user would like 
*	
*/

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LogFunction {
	private int slopeOffset;
	private int horizontalShift;
	private int verticalShift;
	
	private int graphWidth;
	private int graphHeight;
	
	private Color color;
	
	private Graphics g;
	
	private Random r;
	
	private String type;
		
	private final int SCALE = 100;
	
	private Map<Integer, Integer> points;

	// Constructor of the class that initializes all fields
	// Receives int slopeOffset, int horizontalShift, and int verticalShift
	// 		 as parameters so it knows  what function the user is trying to graph
	// Receives String type so it knows whether the user wants to graph a log base 10 
	// 		function or a natural log
	// Receives graphics g so that the function can be graphed onto the 
	// 		the specified drawing panel
	// Receives int graphWidth and int graphHeight so that the function 
	// 		knows the dimensions of the panel that it is being graphed onto
	public LogFunction(int slopeOffset, int horizontalShift, int verticalShift, String type, Graphics g, int graphWidth, int graphHeight) {
		this.slopeOffset = slopeOffset;
		this.horizontalShift = horizontalShift;
		this.verticalShift = verticalShift;
		
		this.type = type;
		
		r = new Random();
		
		color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		
		this.graphWidth = graphWidth;
		this.graphHeight = graphHeight;
		
		points = new HashMap<Integer, Integer>();
		
		this.g = g;
	}
	
	// Draws a logarithmic function onto the specified drawing panel
	public void draw() {
		g.setColor(color);
		
		int x1 = (graphWidth / 2);
		int y1 = (graphHeight / 2);
		
		int x2 = (graphWidth / 2);
		int y2 = (graphHeight / 2);
		
		double i = 0.01;

		if(type.equalsIgnoreCase("log")) {
			graphLog(g, x1, y1, x2, y2, i);
		} else {
			graphLn(g, x1, y1, x2, y2, i);
		}
	}
	
	// Draws a logarithmic function of base 10 onto the specified drawing panel
	private void graphLog(Graphics g, int x1, int y1, int x2, int y2, double i){
		while(x2 <= graphWidth){
			x1 = ((int) (i * SCALE) + ((graphWidth / 2)) + (horizontalShift * SCALE));
			y1 = (int) (-slopeOffset * (Math.log10(i) * SCALE) - (verticalShift * SCALE) + (graphHeight / 2));
			
			x2 = ((int) ((i + .01) * SCALE) + ((graphWidth / 2)) + (horizontalShift * SCALE));
			y2 = (int) (-slopeOffset * (Math.log10(i + .01) * SCALE) - (verticalShift * SCALE) + (graphHeight / 2));
			
			points.put(x1, y1);
			g.drawLine(x1,y1,x2,y2);
			i += .01;
		}
	}
	
	// Draws a natural log function onto the specified drawing panel
	private void graphLn(Graphics g, int x1, int y1, int x2, int y2, double i){
		while(x2 <= graphWidth){
			x1 = ((int) (i * SCALE) + ((graphWidth / 2)) + (horizontalShift * SCALE));
			y1 = (int) (-slopeOffset * (Math.log(i) * SCALE) - (verticalShift * SCALE) + (graphHeight / 2));
			
			x2 = ((int) ((i + .01) * SCALE) + ((graphWidth / 2)) + (horizontalShift * SCALE));
			y2 = (int) (-slopeOffset * (Math.log(i + .01) * SCALE) - (verticalShift * SCALE) + (graphHeight / 2));
			
			points.put(x1, y1);
			g.drawLine(x1,y1,x2,y2);
			i += .01;
		}
	}
	
	// Returns a map of the points of the function that are on the graph
	// There is one point per pixel
	public Map<Integer, Integer> getPoints(){
		return points;
	}
}

