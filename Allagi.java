/*  Crist, R.M., Drumheller, G.L.
	Allagi Class is a grapher. 
		Includes capabilites for graphing trigonometric,
		exponential, polynomial, linear, logarithmic and
		scatter plot functions. When functions interesect 
		these points are indicated.
*/

import java.awt.*;
import java.util.*;

public class Allagi {
	private static ArrayList<Integer> constants;
	private static String delims = "[()+\\*\s]"; // string delimiters used for tokenizing user input
	private static Graph myGraph;
	private static String[] tokens;
	private static String expression;
	
	// Runs Allagi program
	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);
		choosePanelSize(myScanner);
		System.out.println("Enter an expression.");
		printTable(); 
		expression = myScanner.nextLine();
		while (!expression.equalsIgnoreCase("exit")) {
			try
			{
				tokens = getTokens(expression);
				locateConstants(tokens);
				identifyFunction(expression);
				expression = myScanner.nextLine();
			}
			catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid Input: Enter form using table");
				expression = myScanner.nextLine();
			}
		}  	
	}
	
	// choosePanelSize allows user to customize window size.
	// Data then used to create new graphing window.
	// Parameters: Scanner s. Used to read user input.
	public static void choosePanelSize(Scanner s) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		System.out.println("Due to the size of your screen:");
		System.out.println("Your maximum x value is: " + (int)(width / 200));
		System.out.println("Your maximum y value is: " + (int)(height / 200));
		System.out.println("Please enter whole numbers and numbers greater than 0");
		System.out.println("------------------------------------------------------");
		System.out.println();
		
		System.out.print("What would you like to be the x axis max/min: ");
		int xDim = s.nextInt();
		while(xDim > (int)(width / 200) || xDim <= 0) {
			System.out.println();
			System.out.println("Please enter whole numbers and numbers greater than 0");
			System.out.println();
			System.out.print("What would you like to be the x axis max/min: ");
			xDim = s.nextInt();
		}
		System.out.println();
		
		System.out.print("What would you like to be the y axis max/min: ");
		int yDim = s.nextInt();
		while(yDim > (int)(height / 200) || yDim <= 0) {
			System.out.println();
			System.out.println("Please enter whole numbers and numbers greater than 0");
			System.out.println();
			System.out.print("What would you like to be the y axis max/min: ");
			yDim = s.nextInt();
		}
		System.out.println();
		
		myGraph = new Graph(xDim, yDim);
	}

	// Tokenizes string. 
	// Parameters: String expression. Represents mathematical expression
	// 		of type specified in table.
	// Returns: String[]. Represents tokenized expression.
	public static String[] getTokens(String expression) {
		String[] tokens = expression.split(delims);
		return tokens;
	}
	
	// Finds and stores constants in expression 
	//		used for function manipualtion.
	// Parameters: String[] tokens. tokens represents tokenized 
	//		expression.
	public static void locateConstants(String[] tokens) {
		constants = new ArrayList<>();
		for (String token : tokens) {
			if (token.contains("^")) {
				String[] result = token.split("\\^"); // deals with exponential
				try
				{
					int y = Integer.parseInt(result[0]);
					constants.add(y);
				}
				catch (NumberFormatException notExpo) {}
			} else {
			try
			{
				int x = Integer.parseInt(token);
				constants.add(x);
			}
			catch (NumberFormatException notInt) {}
			}
		}
	}
	
	// Identifies type of function to be graphed and 
	//		initializes new graphing. 
	// Paramaters: String input. input represents the 
	//		expression entered by the user.
	public static void identifyFunction(String input) {
		if (input.contains("sin")) {
			myGraph.drawTrig(getA(), getB(), getC(), "sin");
		} else if (input.contains("cos")) {
			myGraph.drawTrig(getA(), getB(), getC(), "cos");
		} else if (input.contains("log")) {
			myGraph.drawLog(getA(), getB(), getC(), "log");
		} else if (input.contains("ln")) {
			myGraph.drawLog(getA(), getB(), getC(), "ln");
		} else if (input.contains("^")) {
			int i = input.indexOf("^");
			char character = input.charAt(i + 1);
			String convert = String.valueOf(character);
			try
			{
				int checkChar = Integer.parseInt(convert);
				myGraph.drawPoly(getA(), getB(), getC());
			}
			catch (NumberFormatException e) {
				myGraph.drawExp(getA(), getB(), getC());
			}
		} else {
			myGraph.drawLinear(getA(), getB());
		}
	}
	
	// Retrieves first constant from getExpression
	// Returns int. Represents first found constant from expression
	public static int getA() {
		return constants.get(0);
	}
	
	// Retrieves second constant from getExpression
	// Returns int. Represents second found constant from expression
	public static int getB() {
		return constants.get(1);
	}
	// Retrieves third constant from getExpression
	// Returns int. Represents third found constant from expression
	public static int getC() {
		return constants.get(2);
	}
	
	// Prints form table for user.
	// Table provides specific data representations of each function
	// 		for users to input accordingly. Characters, a, b, and c
	//		represent constants in all cases.
	public static void printTable() {
		System.out.println("		Form Reference Table		");
		System.out.println("*-------------*--------------------*");
		System.out.println("| Linear      | a*x + b            |");
		System.out.println("*-------------*--------------------*");
		System.out.println("| Polynomial  | a*(x - b)^2 + c    |");
		System.out.println("*-------------*--------------------*");
		System.out.println("| Sin/Cosine  | a*sin(b * (x - c)) |");
		System.out.println("*-------------*--------------------*");
		System.out.println("| Exponential | a^(x - b) + c      |");
		System.out.println("*-------------*--------------------*");
		System.out.println("| logarithmic | a*log(x - b) + c   |");
		System.out.println("*-------------*--------------------*");
		System.out.println("  type \"exit\" to terminate graph  ");	
	}
		
}