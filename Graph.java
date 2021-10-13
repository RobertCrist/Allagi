/*
*	Crist, R.M., Drumheller, G.L.
* 	CSE 143
* 
* 	The Graph class is a class that creates a new graph of the specified size
*	Allows the users to draw differt types of functions on the graph like: linear,
*		polynomial, exponential, logarithmic, and trigonometric
*/


import java.awt.*;
import java.util.*;

public class Graph {
	
	private int graphWidth;
	private int graphHeight;
	private final int SCALE = 100;
	private DrawingPanel panel;
	private Graphics g;
	
	private Map<Integer, ArrayList<Integer>> activePoints;
	
	// Constructor of the class that initializes all the fields
	// Receives xDim so it knows how many units the user wants 
	// 		the x max and x min to be
	// Receives yDim so it knows how many units the user wants 
	// 		the y max and y min to be
	public Graph(int xDim, int yDim) {
		this.graphWidth = 2 * xDim * SCALE;
		this.graphHeight = 2 * yDim * SCALE;
		
		panel = new DrawingPanel(graphWidth, graphHeight);
		panel.setBackground(Color.white);
		
		g = panel.getGraphics();
		
		activePoints = new HashMap<Integer, ArrayList<Integer>>();
		
		makeAxis();
	}
	
	//Draws the axis of the graph and hashes for each unit
	private void makeAxis() {
		
		//draw axis
		g.drawLine(graphWidth/2, 0, graphWidth/2, graphHeight);
	    g.drawLine(0, graphHeight/2, graphWidth, graphHeight/2);
	    // draw x hashes
	    for (int i = 0; i < graphWidth; i ++) {
	    	g.drawLine(SCALE * i, graphHeight/2 - (graphHeight/100), SCALE * i, graphHeight/2 + (graphHeight/100));
	    }
	    // draw y hashes
	    for (int i = 0; i < graphHeight; i ++) {
	    	g.drawLine(graphWidth/2 - (graphWidth/SCALE), SCALE * i, graphWidth/2 + graphWidth/SCALE, SCALE * i);
	    }
	   
	}
	
    // Draws a linear function onto the graph
	// Receives int slope as a parameter so it knows what the 
	//		slope of the function is that is trying to graph
	// Receives int yIntercept as a parameter so it knows what the 
	//		yIntercept of the function is that is trying to graph
	public void drawLinear(int slope, int yIntercept) {
		LinearFunction line = new LinearFunction(slope,yIntercept,g, graphWidth, graphHeight);
		line.draw();
		intersect(line.getPoints());
	}
	
    // Draws a polynomial function of degree 2 function onto the graph
	// Receives slopeOffset slope as a parameter so it knows what the 
	//		slope offset of the function is that is trying to graph
	// Receives int horizontalShift as a parameter so it knows what the 
	//		x value of the vertex is 
	// Receives int verticalShift as a parameter so it knows what the 
	//		y value of the vertex is 
    public void drawPoly(int slopeOffset, int horizontalShift, int verticalShift) {
    	PolynomialFunctions poly = new PolynomialFunctions(slopeOffset, horizontalShift, verticalShift, g, graphWidth, graphHeight);
    	poly.draw();
    	intersect(poly.getPoints());
    }

    // Draws an exponential function onto the graph
	// Receives int base as a parameter so it knows what the 
	//		base of the function is that is trying to graph
	// Receives int horizontalShift as a parameter so it knows  
	//		how much the function is shifted to the right or left 
	// Receives int verticalShift as a parameter so it knows
	//		how much the to shift the function up and down
    public void drawExp(int base, int horizontalShift, int verticalShift) {
    	ExponentialFunction exp = new ExponentialFunction(base, horizontalShift, verticalShift, g, graphWidth, graphHeight);
    	exp.draw();
    	intersect(exp.getPoints());
    }
    
	// Draws an logarithmic function onto the graph
	// Receives int slopeOffset as a parameter so it knows what the 
	//		slopeOffset of the function is that is trying to graph
	// Receives int horizontalShift as a parameter so it knows  
	//		how much the function is shifted to the right or left 
	// Receives int verticalShift as a parameter so it knows
	//		how much the to shift the function up and down
	// Receives String type so it knows whether a logarithmic function
	// 		of base 10 or base e is being graphed
    public void drawLog(int slopeOffset, int horizontalShift, int verticalShift, String type) {
    	LogFunction log = new LogFunction(slopeOffset, horizontalShift, verticalShift, type, g, graphWidth, graphHeight);
    	log.draw();
    	intersect(log.getPoints());
    }

    // Draws an trigonometric function onto the graph
	// Receives int amplitude as a parameter so it knows what the 
	//		amplitude of the function is that is trying to graph
	// Receives int frequency as a parameter so it knows what the 
	//		frequency of the function is that is trying to graph 
	// Receives int phaseShift as a parameter so it knows what the 
	//		phaseShift of the function is that is trying to graph
	// Receives String type so it knows whether a logarithmic function
	// 		of base 10 or base e is being graphed
    public void drawTrig(int amplitude, int frequency, int phaseShift, String type) {
    	TrigFunctions trig = new TrigFunctions(amplitude, frequency, phaseShift, type, g, graphWidth, graphHeight);
    	trig.draw();
    	intersect(trig.getPoints());
    }
    
	// Draws an scatter plot onto the graph
	// Receoves Map<Integer, Integer> plot so it knows what points 
	// 		are trying to be plotted
    public void drawScatter(Map<Integer, Integer> plot) {
    	ScatterPlot scatter = new ScatterPlot(plot, g, graphWidth, graphHeight);
    	scatter.draw();
    	intersect(scatter.getPoints());
    }
    
	// Graphs all intersection points between different functions on the same graph
	// Receives Map<Integer, Integer> points so it knows all the points of the function
	// 		that is being added to the graph
    private void intersect(Map<Integer, Integer> points) { 	
    	for(int x: points.keySet()) {
    		if(activePoints.containsKey(x)) {
    			if(activePoints.get(x).contains(points.get(x))) {
    				intersectFormating(x,points.get(x));
    			} else if(x < graphWidth && activePoints.containsKey(x + 1) && points.containsKey(x + 1)
    					&&  activePoints.get(x).size() == activePoints.get(x + 1).size()) {

    				ArrayList<Integer> currYValues = activePoints.get(x);
    				ArrayList<Integer> nextYValues = activePoints.get(x + 1);

    				for(int i = 0; i < nextYValues.size(); i++) {
    					if((points.get(x) > currYValues.get(i) && points.get(x + 1) < nextYValues.get(i)) ||
        						(points.get(x) < currYValues.get(i) && points.get(x + 1) > nextYValues.get(i))) {
    						intersectFormating(x,points.get(x));
        				}
    				}
    				
    			}
    			activePoints.get(x).add(points.get(x));
    		} else {
    			ArrayList<Integer> yValues = new ArrayList<Integer>();
    			yValues.add(points.get(x));
    			
    			activePoints.put(x, yValues);
    		}
    	}
    }
    
	// Decides what side the interesction point the text should be 
	// 		depending on what the quadrant the intersection point is initializes
	// Receives int x and int y as paramters so it knows what the interectionn point is
    private void intersectFormating(int x, int y) {
    	g.setColor(Color.BLACK);
    	double translatedX = (((double)(x - (graphWidth / 2))) / SCALE);
    	double translatedY = ((-(double)(y - (graphHeight / 2)) / SCALE));
    	
    	if(translatedX == 0) {
    		translatedX = Math.abs(translatedX);
    	}
    	if(translatedY == 0) {
    		translatedY = Math.abs(translatedY);
    	}
    	if(x >= (graphWidth / 2) && y <= (graphHeight / 2)) {
    		g.fillOval(x - 3, y - 3, 6, 6);
    		g.drawString("(" + translatedX + ", " + translatedY + ")", x - 15, y + 15);	
    	} else if(x < (graphWidth / 2) && y < (graphHeight / 2)) {
    		g.fillOval(x - 3, y - 3, 6, 6);
    		g.drawString("(" + translatedX + ", " + translatedY + ")", x + 15, y + 15);
    	} else if(x <= (graphWidth / 2) && y >= (graphHeight / 2)) {
    		g.fillOval(x - 3, y - 3, 6, 6);
    		g.drawString("(" + translatedX + ", " + translatedY + ")", x + 15, y - 15);
    	} else {
    		g.fillOval(x - 3, y - 3, 6, 6);
    		g.drawString("(" + translatedX + ", " + translatedY + ")", x - 15, y - 15);
    	}
    }
		
}


