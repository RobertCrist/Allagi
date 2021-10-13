# Allagi
The project has three main components:
    
   1. Allagi Class
   2. Graph Class
   3. Function classes

Before you run any of the code that we have written, the DrawingPanel class must first be compilied 

When the program is run, the Allagi class will prompt the user to create a new graph. The user is then
able to input expressions. However, these expression are extremely sensative, and must follow the exact input
as shown in the form table (See Allagi class). It is important to note that Allagi only works with Integers.
Fractions or doubles will not compute. When multiple expressions are graphed, intersection points will be indicated 
on the window and coordinates should appear showing the approximate point of intersection.
When the user inputs an expression, for example a linear function as 2*x + 1 (no need to type y =), the graph
window will update with a visual of the expression. This graph window is an active window, so as long as the user
does not enter "exit" they may continue to input expressions that will be graphed to the same window. "exit" or 
closing the graphing window will terminate the session. 

User input must be entered properly. Please follow these guidelines when typing in an expression. 
    1. If you would like a constant to be negative, input the given form, and simply put a negative sign 
        directly attached to the constant. For example, 3*(x - 2)^2 + -1, adds "-1". 
    2. Incase an inproper input is given, the console should catch this error and prompt the user to 
        re-enter input. If this does not happen, close the window and restart the program.
    3. In the case of phase shifts (i.e. where form table shows "x - b), input zero to indicate no phase shift.
    4. If you would like no change that is NOT a phase shift (see line 3), input 1 where a constant appears. 
        For example a*x + b with no manipulations is 1*x + 0. 

Because of the nature of the drawing panel used, our expressions and intersections are not always precise. 
When expressions become complicated, for example a sinusoidal function with a high frequency intersected with 
a parabola, our intersections may lose accuracy. 

Note: Not all intersections for logarithmic functions will work properly because we 
        had to do a workaround for graphing their asymptotic behavior. 

Reading input was one of the final additions to the project. Included in our submission will be an Allagi2.0 file
that allows the user to manually enter constants for expressions.  

Lastly, we recommend watching our short video for a walk-through on how to use Allagi. 
https://drive.google.com/file/d/1oYKLrn-YGSamX2sHQJpXf0hN8nd-ZARS/view?usp=sharing
