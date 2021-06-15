package Classifier;

import java.util.ArrayList;

import DataStructures.Feature;

public class Validator {
	// Leave-One-Out Validator
	NearestNeighbor NN;
	public Validator(NearestNeighbor N) {
		this.NN = N;
	}
	
	public double evaluate(ArrayList<Feature> features) {
		double accuracy = 0.0;
		int correct = 0;
		
		for(int i = 0; i < NN.instanceNum; i++) {
			Instance curr = NN.trained.get(i);
			int predict = NN.test(curr, 1, features);
			
			if(predict == curr.realClass) {
				correct++;
			}
		}
		
		accuracy = (double)correct / NN.instanceNum;
		
		System.out.println("The accuracy of feature subset " + printFeatures(features) + " is " + accuracy);
		
		return accuracy;
	}
	
	public String printFeatures(ArrayList<Feature> features) {
		String s = "{";
		for(int i = 0; i < features.size()-1; i++) {
			s += features.get(i).label;
			s += " ";
		}
		s += features.get(features.size()-1).label;
		s += "}";
		return s;
	}
}
