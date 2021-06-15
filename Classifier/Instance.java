package Classifier;

import java.util.Arrays;

public class Instance {
	public double[] features;
	public int label;
	int realClass;
	double distance;
	
	public Instance() {
		distance = 0;
		label = 0;
		realClass = 0;
	}
	
	public Instance(double dist) {// this is test constructor for priority queue
		distance = dist;
		label = 0;
		realClass = 0;
		features = new double[]{0.0, 0.0, 0.0};
	}
	
	public Instance(int label, int c, double[] f){
		this.label = label;
		realClass = c;
		features = f;
		distance = 0;
	}
	
	public Instance(Instance ins) {// copy constructor
		this.label = ins.label;
		this.realClass = ins.realClass;
		this.distance = 0;
		this.features = ins.features;
	}
	
	public void print() {
		System.out.println("Label: " + label + "\n" +
				"Class: " + realClass + "\n" + 
				"Distance: " + distance + "\n" +
				"Features: " + Arrays.toString(features));
	}
}
