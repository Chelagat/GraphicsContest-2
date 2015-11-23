import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;

import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;


import acm.graphics.GLine;
import acm.graphics.GMath;
import acm.graphics.GOval;

import acm.graphics.*;



public class BlankClass extends GCanvas
implements ComponentListener{
	private  ArrayList<ArrayList<Double>> array;
	private double APPLICATION_WIDTH;
	private GLabel label;
	private double x1;
	private double y1;
	private GLabel root;


	public  BlankClass(){
		addComponentListener(this);
		array = new ArrayList<ArrayList<Double>>();

	}
	public void addEntry(ArrayList<Double> coefficient) {
		array.add(coefficient);
	}
	public void clear(){
		removeAll();
		array.clear();
	}

	

	private void showGraphFunction(ArrayList<Double>coefficient){
		String result = "f(x)= ";
		int degree = coefficient.size()-1;
		double coeff = (double) coefficient.get(0);
		if (coeff==1){
			result += " "+ "x^"+degree + "";
		} else if (coeff!=0){
			result += " "+ coeff + "x^"+degree + "";
		} 
		--degree;
		for (int i=1; i<coefficient.size(); i++){
			coeff = (double) coefficient.get(i);
			if(degree == 0){
				result+=" +" + coeff;
			} else{
				if (coeff==1){
					result += " +"+ "x^"+degree;
				} else if (coeff!=0){
					result += " +"+ coeff + "x^"+degree;
				} 
			}
			--degree;
		}
		GLabel function = new GLabel(result,0, getHeight()-30);
		add(function);
	}

	public void update(){
		removeAll();

		APPLICATION_WIDTH = getWidth();
		label = new GLabel("");
		add(label,50,50);
		for(int i=0; i<array.size(); i++){
			ArrayList<Double> coefficient = array.get(i);
			showGraphFunction(coefficient);
			drawGraph(coefficient);
		}

	}

	public void findRoots(ArrayList<Double>coefficient, double firstGuess){
		String result = "Roots: ";
		root = new GLabel("", 0,100);
		add(root);
		int degree = coefficient.size()-1; 
		if(firstGuess==0){


			int count = degree;
			for(int x=-40;x<40; x+=1){
				count = degree;
				int y=0;
				for (int i=0; i<coefficient.size(); i++){
					double coeff = (double) coefficient.get(i);
					y += coeff* Math.pow(x, count);
					--count;
				}
				if (y==0){
					System.out.println(x);
					GOval oval = new GOval(getWidth()/2 -10 + x*10, getHeight()/2-10, 20, 20);
					add(oval);
					result+=" "+x+",";
					root.setLabel(result);
				}
			}
		} 
		else {
			newtonApproximation(coefficient, degree, firstGuess);
			if(fX<=tolerance){
				result+="     "+ n;
				GOval oval = new GOval(getWidth()/2 -10 + n*10, getHeight()/2-10, 20, 20);
				add(oval);
				root.setLabel(result);
			}
			else {
				root.setLabel(result + "There are no roots found");
			}
		}
	}




	private double tolerance = 0.0000000001;
	private int maxCount = 200;
	private double x2;
	private double n;
	private double fX;
	private void newtonApproximation(ArrayList<Double> coefficient, int degree, double x){
		n = x;
		fX = getY(coefficient, n, degree);
		double derivativeX = getDerivative(coefficient, n, degree);
		x2 = n - (fX/derivativeX);
		n = x2;
		--maxCount;
		System.out.println(fX);
		if (maxCount>0){
			newtonApproximation(coefficient, degree, n);
		}	

	}
	private double getY(ArrayList<Double> coefficient, double x, int degree){
		double y= 0;
		int count = degree;
		for (int i=0; i<coefficient.size(); i++){
			double coeff = (double) coefficient.get(i);
			y += coeff* Math.pow(x, count);
			--count;
		}

		return y;

	}


	private double getDerivative(ArrayList coefficient, double x, int degree){
		int count = degree;
		double y =0;
		for(int i=0; i<coefficient.size(); i++){
			double coeff = (double)coefficient.get(i);
			coeff = coeff*count;
			y+=coeff*Math.pow(x,count-1);
			--count;
		}

		return  y;

	}

	public void drawSineFunction(){
		double x = 0.000;
		while(true){
			double y=Math.sin(x);
			GOval oval = new GOval(0.001, 0.001);
			oval.setColor(Color.BLUE);
			add(oval, APPLICATION_WIDTH/2+ x*50-oval.getWidth()/2,getHeight()/2-y*50-oval.getWidth()/2);
			if(oval.getX()>getWidth()) break;
			x+=0.001;
		}

		x = -0.000;
		while(true){
			double y = Math.sin(x);
			GOval oval = new GOval(0.001, 0.001);
			oval.setColor(Color.BLUE);
			add(oval, APPLICATION_WIDTH/2+ x*50-oval.getWidth()/2,getHeight()/2-y*50-oval.getWidth()/2);
			if(oval.getX()<0) break;
			x-=0.001;

		}
	}
	public void drawCosineFunction(){
		double x = 0.000;
		while(true){
			double y=Math.cos(x);
			GOval oval = new GOval(0.001, 0.001);
			oval.setColor(Color.GREEN);
			add(oval, APPLICATION_WIDTH/2+ x*50-oval.getWidth()/2,getHeight()/2-y*50-oval.getWidth()/2);
			if(oval.getX()>getWidth()) break;
			x+=0.001;
		}

		x = -0.000;
		while(true){
			double y = Math.cos(x);
			GOval oval = new GOval(0.001, 0.001);
			oval.setColor(Color.GREEN);
			add(oval, APPLICATION_WIDTH/2+ x*50-oval.getWidth()/2,getHeight()/2-y*50-oval.getWidth()/2);
			if(oval.getX()<0) break;
			x-=0.001;

		}
	}
	public void drawTanFunction(){
		double x = 0.000;
		while(true){
			double y=Math.tan(x);
			GOval oval = new GOval(0.001, 0.001);
			oval.setColor(Color.ORANGE);
			add(oval, APPLICATION_WIDTH/2+ x*50-oval.getWidth()/2,getHeight()/2-y*50-oval.getWidth()/2);
			if(oval.getX()>getWidth()) break;
			x+=0.001;
		}

		x = -0.000;
		while(true){
			double y = Math.tan(x);
			GOval oval = new GOval(0.001, 0.001);
			oval.setColor(Color.ORANGE);
			add(oval, APPLICATION_WIDTH/2+ x*50-oval.getWidth()/2,getHeight()/2-y*50-oval.getWidth()/2);
			if(oval.getX()<0) break;
			x-=0.001;

		}
	}
	public void drawInverse(ArrayList coefficient){
		int degree = coefficient.size()-1; 
		double x = 0.000;
		while(true){
			double y=0;
			int count = degree;
			for (int i=0; i<coefficient.size(); i++){
				double coeff = (double) coefficient.get(i);
				y += coeff* Math.pow(x, count);
				--count;
			}
			GOval point = new GOval(0.0001,0.0001);
			point.setColor(Color.RED);
			add(point, APPLICATION_WIDTH/2+ y*10-point.getWidth()/2,getHeight()/2-x-point.getWidth()/2);
			if (point.getX()<0 || point.getX()>getWidth()) break;
			x+=0.001;
		}
		x =0.000;
		while(true){
			double y=0;
			int count = degree;
			for (int i=0; i<coefficient.size(); i++){
				double coeff = (double) coefficient.get(i);
				y += coeff* Math.pow(x, count);
				--count;
			}

			GOval point2 = new GOval(0.0001,0.0001);
			point2.setColor(Color.RED);
			add(point2, APPLICATION_WIDTH/2+ y*10-point2.getWidth()/2,getHeight()/2- x-point2.getWidth()/2);
            if (point2.getX()<0 || point2.getX()>getWidth()) break;
			x-=0.001;

		} 
	}

	public void drawGraph(ArrayList coefficient){
		drawYAxis();
		drawXAxis();
		int degree = coefficient.size()-1; 
		double x = 0.000;
		while(true){
			double y=0;
			int count = degree;
			for (int i=0; i<coefficient.size(); i++){
				double coeff = (double) coefficient.get(i);
				y += coeff* Math.pow(x, count);
				--count;
			}
			GOval point = new GOval(0.0001,0.0001);
			add(point, APPLICATION_WIDTH/2+ x*10-point.getWidth()/2,getHeight()/2-y-point.getWidth()/2);
			if (point.getY()<0 || point.getY()>getHeight()) break;
			x+=0.001;
		}
		x =0.000;
		while(true){
			double y=0;
			int count = degree;
			for (int i=0; i<coefficient.size(); i++){
				double coeff = (double) coefficient.get(i);
				y += coeff* Math.pow(x, count);
				--count;
			}

			GOval point2 = new GOval(0.0001,0.0001);
			add(point2, APPLICATION_WIDTH/2+ x*10-point2.getWidth()/2,getHeight()/2- y-point2.getWidth()/2);

			if (point2.getY()<0 || point2.getY()>getHeight()) break;
			x-=0.001;

		} 
	}
	private void drawXAxis(){
		GLine xAxis = new GLine(0, getHeight()/2,APPLICATION_WIDTH, getHeight()/2);
		double count2 = 0;
		add(xAxis);
		double width = APPLICATION_WIDTH/30;
		double xLine = APPLICATION_WIDTH/2;
		int counter = 0;
		for (int i=0; i<20; ++i){
			GLine line = new GLine(xLine,getHeight()/2-5, xLine, getHeight()/2+5);
			add(line);
			++counter;
			if(counter%5==0){
				GLabel label = new GLabel(""+count2, xLine, getHeight()/2+15);
				label.setFont("Helvetica-plain-10");
				add(label);
			}

			xLine-=width;
			count2-=2.5;
		}
		xLine = APPLICATION_WIDTH/2;
		count2 = 0;
		counter = 0;
		for (int i=0; i<20; ++i){
			GLine line = new GLine(xLine,getHeight()/2-5, xLine, getHeight()/2+5);
			add(line);
			++counter;
			if(counter%5==0){
				GLabel label = new GLabel(""+count2, xLine, getHeight()/2+15);
				label.setFont("Helvetica-plain-10");
				add(label);
			}
			count2+=2.5;
			xLine+=width;
		}
	}
	private void drawYAxis(){

		int count1 = 0;
		int counter1 = 0;
		GLine yAxis = new GLine(APPLICATION_WIDTH/2,0,APPLICATION_WIDTH/2,getHeight() );
		add(yAxis);
		int height = getHeight()/20;
		int yLine = getHeight()/2;
		for (int i=0; i<20;++i){
			GLine line = new GLine(APPLICATION_WIDTH/2-5,yLine, APPLICATION_WIDTH/2+5,yLine);
			add(line);
			++counter1;
			if(counter1%1==0 &&counter1!=1){
				GLabel label = new GLabel(""+count1,APPLICATION_WIDTH/2-20,yLine+5 );
				label.setFont("Helvetica-plain-10");
				add(label);
			}
			yLine+=height;
			count1-=20;

		}
		yLine = getHeight()/2;
		counter1=0;
		count1=0;
		for (int i=0; i<20;++i){
			GLine line = new GLine(APPLICATION_WIDTH/2-5,yLine, APPLICATION_WIDTH/2+5,yLine);
			add(line);
			++counter1;
			if(counter1%1==0 && counter1!=1){
				GLabel label = new GLabel(""+count1,APPLICATION_WIDTH/2-20,yLine+5 );
				label.setFont("Helvetica-plain-10");
				add(label);
			}
			yLine-=height;
			count1+=20;
		}
	}
     
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	



}

