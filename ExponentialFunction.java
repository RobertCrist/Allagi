/* Crist, R.M., Drumheller, G.L.
   Implements ExponentialFunction class. 
   Class provides methods for drawing 
   exponential functions. 
 */

import java.awt.*;
import java.util.*;

public class ExponentialFunction {
	private int base;
	private int horizontalShift;
	private int verticalShift;
	private int graphWidth;
	private int graphHeight;
	private Color color;
	private Random r;
	private Graphics g;
	private final int SCALE = 100;
	private Map<Integer, Integer> points;

	// Instantiates new ExponentialFunction.
	// Parameters: int base, int horizontalShift, and int verticalShift
	//			represent function manipulations.  
	//			Other params: Graphics g, int graphWidth, int graphHeight.
	public ExponentialFunction(int base, int horizontalShift, int verticalShift, Graphics g, int graphWidth, int graphHeight) {
		this.base = base;
		this.horizontalShift = horizontalShift;
		this.verticalShift = verticalShift;
		
		r = new Random();
		color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		
		this.graphWidth = graphWidth;
		this.graphHeight = graphHeight;
		points = new HashMap<Integer, Integer>();
		
		this.g = g;
	}
	
	// Draws exponential function
	public void draw() {
		g.setColor(color);
		int x1 = (graphWidth / 2);
		int y1 = (graphHeight / 2);
		int x2 = (graphWidth / 2);
		int y2 = (graphHeight / 2);
		double i = 0;
		
		while(x2 <= graphWidth && y2 <= graphHeight && y2 >= 0){
			x1 = ((int) (i * SCALE) + ((graphWidth / 2)) - (horizontalShift * SCALE));
			y1 = (int) (-(Math.pow(base, i) * SCALE) - (verticalShift * SCALE) + (graphHeight / 2));
			
			x2 = ((int) ((i + .01) * SCALE) + ((graphWidth / 2)) - (horizontalShift * SCALE));
			y2 = (int) (-(Math.pow(base, (i + .01)) * SCALE) - (verticalShift * SCALE) + (graphHeight / 2));
			
			points.put(x1, y1);
			g.drawLine(x1,y1,x2,y2);
			i += .01;
		}
		
		x1 = (graphWidth / 2);
		y1 = (graphHeight / 2);
		
		x2 = (graphWidth / 2);
		y2 = (graphHeight / 2);
		i = 0;

		while(x2 >= 0 && y2 <= graphHeight && y2 >= 0){
			x1 = ((int) (i * SCALE) + ((graphWidth / 2)) - (horizontalShift * SCALE));
			y1 = (int) (-(Math.pow(base, i) * SCALE) - (verticalShift * SCALE) + (graphHeight / 2));
			
			x2 = ((int) ((i + .01) * SCALE) + ((graphWidth / 2)) - (horizontalShift * SCALE));
			y2 = (int)  (-(Math.pow(base, (i + .01)) * SCALE) - (verticalShift * SCALE) + (graphHeight / 2));
			
			points.put(x1, y1);
			g.drawLine(x1,y1,x2,y2);	
			i -= .01;
		}
	}
	
	// Catalogs x and y coordinates for function. 
	// Returns Map<Integer, Integer> where each int
	// 		represents an x and y coordinate
	public Map<Integer, Integer> getPoints(){
		return points;
	}
}