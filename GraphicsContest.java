/*
 * File: GraphicsContest
 * Name: Norah Borus
 * Section Leader: John Merriman Sholar
 * GRAPHING CALCULATOR
 * The program simulates a graphing calculator for polynomials and 
 * basic trigonometric functions. The user is asked, via IO dialog for the information
 * regarding the polynomial function to be represented graphically(i.e. the degree,
 * and coefficients, and constant). This information is stored in an arraylist and passed onto a 
 * GCanvas, where the graph is drawn.
 * The inverse function of the graph can be drawn.
 * The roots of the function can be gotten. If the roots are not whole numbers, they are approximated using 
 * Newton's approximation, taking in a first guess(x1) from the user. The roots are circled if they are 
 * found. If there are no roots, or if the user picks a bad first guess, they are informed that there are no roots found.
 * Sin(x), Cos(x) and tan(x) functions are options that the user can click on if he/she wants
 * to see a graphical representation.
 */
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import acm.program.Program;
import acm.gui.DoubleField;
import acm.io.IODialog;

public class GraphicsContest extends Program {
	private DoubleField firstGuess;
	private JButton root;
	private JButton clear;
	private BlankClass canvas;
	private int degree;
	private JButton inverse;
	private ArrayList<Double> coefficient;
	private JButton sine;
	private JButton cosine;
	private JButton tan;
	

	public void init(){
		clear = new JButton("Clear");
		add(clear, NORTH);
		root = new JButton("Root");
		add(root,NORTH);
		inverse = new JButton("Inverse");
		add(inverse, NORTH);
		sine = new JButton("Sin(x)");
		add(sine, SOUTH);
		cosine = new JButton("Cos(x)");
		add(cosine, SOUTH);
		tan = new JButton("Tan(x)");
		add(tan, SOUTH);
		add(new JLabel("First guess for Newton's Approximation"), NORTH);
		firstGuess = new DoubleField(1000);
		add(firstGuess, NORTH);
		firstGuess.addActionListener(this);
		addActionListeners();
		canvas = new BlankClass();
		add(canvas);
		coefficient = new ArrayList<Double>();
		IODialog dialog = getDialog();
		degree = dialog.readInt("Enter degree of polynomial:");
		int count = degree;
		for (int i = 0; i < degree; i++) {

			String line = dialog.readLine("Enter coefficient "+count);
			double x = Double.parseDouble(line);
			coefficient.add(x);
			--count;
		}
		double x = dialog.readInt("Enter constant: ");
		coefficient.add(x);
		drawGraph();


	}

	private void drawGraph(){
        canvas.addEntry(coefficient);
		canvas.update();
    }
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==clear){
			canvas.clear();
			coefficient.clear();
			IODialog dialog = getDialog();
			degree = dialog.readInt("Enter degree of polynomial");
			int count = degree;
			for (int i = 0; i < degree; i++) {

				String line = dialog.readLine("Enter coefficient "+count);
				double x = Double.parseDouble(line);
				coefficient.add(x);
				--count;
			}
			double x = dialog.readInt("Enter constant: ");
			coefficient.add(x);
			drawGraph();
		} else if(e.getSource()==root || e.getSource()==firstGuess){
			canvas.findRoots(coefficient, firstGuess.getValue());
		} else if(e.getSource()==sine){
			canvas.drawSineFunction();
		} else if(e.getSource()==cosine){
			canvas.drawCosineFunction();
		} else if(e.getSource()==tan){
			canvas.drawTanFunction();
		} else if(e.getSource()==inverse){
			canvas.drawInverse(coefficient);
		}

	}
}

