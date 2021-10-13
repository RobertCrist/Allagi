/* Crist, R.M., Drumheller, G.L.
   Implements TrigFunctions class. Class provides methods
   for drawing sin and cosine functions. 
 */

import java.awt.*;
import java.util.*; 

public class TrigFunctions {
	private int amplitude;
	private int frequency;
	private int phaseShift;
	private String type;
	private Color color;
	private Random r;
	private int graphWidth;
	private int graphHeight;
	private final int SCALE = 100;
	private Map<Integer, Integer> points;
	private Graphics g;
	
	// Instantiates new TrigFunction. 
	// Parameters: int amplitude, int frequency, int phaseshift, 
	//		String type, Graphics g, int graphWidth, int graphHeight.
	//		type represents cosine for sin function. 
	public TrigFunctions(int amplitude, int frequency, int phaseShift, String type, Graphics g, int graphWidth, int graphHeight) {
		this.amplitude = amplitude;
		this.frequency = frequency;
		this.phaseShift = phaseShift;
		this.type = type;
		
		r = new Random();
		color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		
		this.graphWidth = graphWidth;
		this.graphHeight = graphHeight;
		points = new HashMap<Integer, Integer>();

		this.g = g;
	}
	
	// Draws trigonometric function
	public void draw() {
		g.setColor(color);
		int x1 = graphWidth / 2;
		int y1 = graphHeight / 2;
		int x2 = graphWidth / 2;
		int y2 = graphHeight / 2;
		double i = 0.0;

		if(type.equalsIgnoreCase("sin")) {
			graphSin(g, x1, y1, x2, y2, i);
		} else {
			graphCos(g, x1, y1, x2, y2, i);
		}
	}
	
	// Graphs sin funciton. 
	// Parameters: Graphics g. int x1-x2, int y1-y1, double i. 
	// 		double i represents counting variable. X's and Y's are coordinates.
	private void graphSin(Graphics g, int x1, int y1, int x2, int y2, double i) {
		while (x2 <= graphWidth) {
			x1 = (int) ((i * SCALE) + (graphWidth / 2));
			y1 = (int) (-amplitude * (Math.sin(frequency * 
					   (i - phaseShift * SCALE)) * SCALE) + (graphHeight / 2));
			x2 = ((int) (((i + .01) * SCALE) + (graphWidth / 2)));
			y2 =  (int) (-amplitude * (Math.sin(frequency * 
				        ((i - phaseShift * SCALE) + .01)) * SCALE) + (graphHeight / 2));
			points.put(x1, y1);
			g.drawLine(x1, y1, x2, y2);	
			i += .01;
		}
		
		x1 = graphWidth / 2;
		y1 = graphHeight / 2;
		x2 = graphWidth / 2;
		y2 = graphHeight / 2;
		i = 0.0;
		
		while (x2 >= 0) {
			x1 = (int) ((i * SCALE) + (graphWidth / 2));
			y1 = (int) (-amplitude * (Math.sin(frequency * 
					   (i - phaseShift * SCALE)) * SCALE) + (graphHeight / 2));
			x2 = ((int) (((i + .01) * SCALE) + (graphWidth / 2)));
			y2 =  (int) (-amplitude * (Math.sin(frequency * 
					    ((i - phaseShift * SCALE) + .01)) * SCALE) + (graphHeight / 2));
			points.put(x1, y1);
			g.drawLine(x1, y1, x2, y2);	
			i -= .01;
		}
	}
	
	// Graphs cos funciton. 
	// Parameters: Graphics g. int x1-x2, int y1-y1, double i. 
	// 		double i represents counting variable. X's and Y's are coordinates.
	private void graphCos(Graphics g, int x1, int y1, int x2, int y2, double i) {
		while (x2 <= graphWidth) {
			x1 = (int) ((i * SCALE) + (graphWidth / 2));
			y1 = (int) (-amplitude * (Math.cos(frequency * 
					   (i - phaseShift * SCALE)) * SCALE) + (graphHeight / 2));
			x2 = ((int) (((i + .01) * SCALE) + (graphWidth / 2)));
			y2 =  (int) (-amplitude * (Math.cos(frequency * 
				        ((i - phaseShift * SCALE) + .01)) * SCALE) + (graphHeight / 2));
			points.put(x1, y1);
			g.drawLine(x1, y1, x2, y2);	
			i += .01;
		}
		
		x1 = graphWidth / 2;
		y1 = graphHeight / 2;
		x2 = graphWidth / 2;
		y2 = graphHeight / 2;
		i = 0.0;

		while (x2 >= 0) {
			x1 = (int) ((i * SCALE) + (graphWidth / 2));
			y1 = (int) (-amplitude * (Math.cos(frequency * 
					   (i - phaseShift * SCALE)) * SCALE) + (graphHeight / 2));
			x2 = ((int) (((i + .01) * SCALE) + (graphWidth / 2)));
			y2 =  (int) (-amplitude * (Math.cos(frequency * 
					    ((i - phaseShift * SCALE) + .01)) * SCALE) + (graphHeight / 2));
			
			points.put(x1, y1);
			g.drawLine(x1, y1, x2, y2);	
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