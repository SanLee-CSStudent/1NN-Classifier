package Algorithms;

import java.util.PriorityQueue;


import DataStructures.State;

public class ForwardSelection extends Search{
	
	public ForwardSelection(int f) {
		super(f);
		// TODO Auto-generated constructor stub
		initial = new State();
	}

	@Override
	public void runSearch() {
		// TODO Auto-generated method stub
		PriorityQueue<State> frontier = new PriorityQueue<State>(11, sc);
		frontier.add(initial);
		updateMax(initial);
		
		System.out.printf("Using feature(s) {" + initial.printFeatures() + "}, accuracy is %.2f%s\n\n", initial.printPercent(), "%");
		System.out.println("Beginning Search...");
		while(true) {
			if(frontier.isEmpty()) {
				break;
			}
			State curr = frontier.poll();
			if(curr.accuracy < max) {
				printResult(maxState);
				break;
			}
			frontier = expand(frontier, curr);
		}
	}

	@Override
	public PriorityQueue<State> expand(PriorityQueue<State> pq, State s) {
		// TODO Auto-generated method stub
		State localMax = new State();
		for(int i = 1; i <= featureNum; i++) {
			if(s.find(i)) {
				continue;
			}
			State child = ops.addFeature(s, i);
			pq.add(child);
			updateMax(child);
			
			if(i == 1) {
				localMax.updateState(child);
			}
			
			if(localMax.accuracy < child.accuracy) {
				localMax.updateState(child);
			}
			
			System.out.printf("Using feature(s) {" + child.printFeatures() + "}, accuracy is %.2f%s\n", child.printPercent(), "%");
		}
		System.out.println("");
		if(localMax.accuracy < maxState.accuracy) {
			System.out.println("Warning! Accuracy has decreased.");
		}
		System.out.printf("Among child states, feature set {" + localMax.printFeatures() + "} was best, accuracy is %.2f%s\n\n", localMax.printPercent(), "%");
		return pq;
	}
}
