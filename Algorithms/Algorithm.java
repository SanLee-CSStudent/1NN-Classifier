package Algorithms;

import java.util.ArrayList;

import Classifier.NearestNeighbor;
import Classifier.Validator;
import DataStructures.Feature;

public class Algorithm {
	private int featureNum;
	private Search search;
	private Validator eval;
	private NearestNeighbor NN;
	ArrayList<Feature> features = new ArrayList<Feature>();
	
	private long elapsedTimeTrain = 0;
	private long elapsedTimeTest = 0;
	private long elapsedTimeNormalize = 0;
	
	// specialized constructor for project part 2
	public Algorithm(ArrayList<String> s, int[] features) {
		NN = new NearestNeighbor(s);
		eval = new Validator(NN);
		// Pass validator loaded with nearest neighbor classifier to each algorithm to use them in State
		
		for(int i: features) {
			this.features.add(new Feature(i));
		}
		System.out.println("Start evaluating with feature subset of " + eval.printFeatures(this.features) + "...");
		long startTest = System.currentTimeMillis();
		eval.evaluate(this.features);
		long endTest = System.currentTimeMillis();
		System.out.println("Finished evaluating.");
		elapsedTimeTest = endTest - startTest;
		System.out.println("Time taken in evaluating dataset:  " + elapsedTimeTest + "ms");
	}
	
	public Algorithm(int features) {
		featureNum = features;
		
	}
	
	public void run(int algorithms) {
		if(algorithms == 1) {
			// run forward selection algorithm
			search = new ForwardSelection(featureNum);
			search.runSearch();
		}
		else if(algorithms == 2) {
			// run backward elimination algorithm
			search = new BackwardElimination(featureNum);
			search.runSearch();
		}
		else {
			System.out.println("Invalid Response. Terminating...");
			System.exit(0);
		}
	}
}
