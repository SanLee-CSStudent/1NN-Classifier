package Algorithms;

import java.util.Comparator;

import DataStructures.State;

public class StateComparator implements Comparator<State>{

	@Override
	public int compare(State o1, State o2) {
		// TODO Auto-generated method stub
		if(o1.accuracy < o2.accuracy) {
			return 1;
		}
		
		if(o1.accuracy > o2.accuracy) {
			return -1;
		}
		return 0;
	}

}
