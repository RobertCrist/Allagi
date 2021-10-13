/* Crist, R.M., Drumheller, G.L.
   Implements ScatterPlot class. 
   Class provides methods to create and draw
   a scatter plot representative or x and
   y coordinates on a cartesian plane. 
 */

import java.awt.*;
import java.util.*;

public class ScatterPlot {
	private Map<Integer, Integer> plot;
	private int DOT_RADIUS = 4;
	private int graphWidth;
	private int graphHeight;
	private Random r;
	private Color color;
	private final int SCALE = 100;
	private Graphics g;

	// Instantiates new ScatterPlot. 
	// Parameters: Map<Integer, Integer> plot, Graphics g, int graphWidth, int graphHeight.
	//		plot represents a mapping of each x coordinate to its respective y coordinate.
	public ScatterPlot(Map<Integer, Integer> plot, Graphics g, int graphWidth, int graphHeight) {
		this.plot = plot;
		this.graphWidth = graphWidth;
		this.graphHeight = graphHeight;
		
		r = new Random();
		color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		
		this.g = g;
	}

	// Draws scatter plot
	public void draw() {
		g.setColor(color);
		for (int x : plot.keySet()) {
			int y = adjustY(plot.get(x));
			x = adjustX(x);
			g.drawOval(x, y, DOT_RADIUS, DOT_RADIUS);
			g.fillOval(x, y, DOT_RADIUS, DOT_RADIUS);
		}
	}

	// Helper method adjusts y coordinate.
	// Returns integer representing adjusted y coordinate.
	private int adjustY(int y) {
		return (y + (graphHeight / 2) - (y * SCALE) - (DOT_RADIUS / 2));
	}

	// Helper method adjusts x coordinate.
	// Returns integer representing adjusted x coordinate.
	private int adjustX(int x) {
		return (x + (graphWidth / 2) + (x * SCALE) - (DOT_RADIUS / 2));
	}
	
	// Catalogs x and y coordinates for function. 
	// Returns Map<Integer, Integer> where each int
	// 		represents an x and y coordinate
	public Map<Integer, Integer> getPoints(){
		return plot;
	}
}