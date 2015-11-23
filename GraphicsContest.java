
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
		firstGuess = new DoubleField();
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
		} else if(e.getSource()==root){
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

