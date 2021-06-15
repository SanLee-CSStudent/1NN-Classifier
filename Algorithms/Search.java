package Algorithms;

import java.util.PriorityQueue;

import DataStructures.Operator;
import DataStructures.State;

public abstract class Search {
	protected int featureNum;
	protected State initial;
	protected State maxState;
	protected Operator ops;
	protected StateComparator sc;
	double max;
	
	public Search(int f) {
		max = 0;
		maxState = new State();
		sc = new StateComparator();
		ops = new Operator();
		featureNum = f;
	}
	
	public abstract void runSearch();
	
	public abstract PriorityQueue<State> expand(PriorityQueue<State> pq, State s);
	
	protected void updateMax(State s) {
		if(s.accuracy > max) {
			max = s.accuracy;
			maxState.updateState(s);
		}
	}
	
	protected void printResult(State s) {
		System.out.println("Finished search!!");
		System.out.printf("The best feature subset is {" + maxState.printFeatures() + "} with an accuracy of %.2f%s\n", maxState.printPercent(), "%");
	}
}
