package Classifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import DataStructures.Feature;

public class NearestNeighbor {
	
	ArrayList<String> lines;
	Map<Integer, Instance> trained;
	int featureNum;
	int instanceNum;
	public long elapsedTimeTrain = 0;
	public long elapsedTimeNormalize = 0;
	
	public NearestNeighbor(ArrayList<String> lines) {
		this.lines = lines;
		trained = new HashMap<Integer, Instance>();
		instanceNum = lines.size();
		
		// Specific to part 2
		part2();
	}
	
	private void part2() {
		System.out.println("Start training...");
		long startTrain = System.currentTimeMillis();
		train();
		long endTrain = System.currentTimeMillis();
		elapsedTimeTrain = endTrain - startTrain;
		featureNum = trained.get(0).features.length;
		System.out.println("Finished training with dataset of " + featureNum + " features and " + instanceNum + " instances");
		System.out.println("Time taken in training dataset:    " + elapsedTimeTrain + "ms\n");

		System.out.println("Start normalizing...");
		long startNormalize = System.currentTimeMillis();
		normalize();
		long endNormalize = System.currentTimeMillis();
		elapsedTimeNormalize = endNormalize - startNormalize;
		System.out.println("Finished normalizing.");
		System.out.println("Time taken in normalizing dataset: " + elapsedTimeNormalize + "ms\n");
	}
	
	private void train() {
		for(int i = 0; i < lines.size(); i++) {
			String[] s = lines.get(i).split("[ ]+");
			// System.out.println(Arrays.toString(s));
			double[] fs = new double[s.length-2];
			for(int j = 0; j < fs.length; j++) {
				fs[j] = Double.parseDouble(s[j+2]);
			}
			// System.out.println(Arrays.toString(fs) + "\n");
			
			Instance ins = new Instance(i, (int)Double.parseDouble(s[1]), fs);
			trained.put(ins.label, ins);
		}
	}
	
	public int test(Instance ins, int k, ArrayList<Feature> subset) {
		int predictedClass = 1;
		PriorityQueue<Instance> neighbors = new PriorityQueue<Instance>((ins1, ins2) -> {
			if(ins1.distance > ins2.distance) {
				return 1;
			}
			else if(ins1.distance == ins2.distance) {
				return 0;
			}
			else {
				return -1;
			}
		});
		
		//test case
		/*k=1;
		trained.clear();
		trained.put(0, new Instance(0, 2, new double[]{2.0, 3.0}));
		trained.put(1, new Instance(1, 1, new double[]{1.0, 1.0}));
		trained.put(2, new Instance(2, 2, new double[]{3.0, 3.0}));
		subset.clear();
		subset.add(new Feature(1));
		subset.add(new Feature(2));
		ins = new Instance(3, 1, new double[] {1.0, 2.0});*/
		
		/* neighbors.add(new Instance(1.0));
		neighbors.add(new Instance(3.0));
		neighbors.add(new Instance(2.0));
		
		while(!neighbors.isEmpty()) {
			Instance curr = neighbors.poll();
			curr.print();
		} */
		for(int i = 0; i < trained.size(); i++) {
			if(i == ins.label) {
				continue;
			}
			// System.out.println(i);
			Instance curr = new Instance(trained.get(i));
			double dist = 0;
			for(int j = 0; j < subset.size(); j++) {
				int featureIndex = subset.get(j).label - 1;
				dist += Math.pow(ins.features[featureIndex] - curr.features[featureIndex], 2);
			}
			curr.distance = Math.sqrt(dist);
			neighbors.add(curr);
		}
		// class label, frequency
		TreeMap<Integer, Integer> vote = new TreeMap<Integer, Integer>((o1, o2)->{
			if(o1 < o2) {
				return 1;
			}
			else if(o1 == o2) {
				return 0;
			}
			else {
				return -1;
			}
		});
		int i = 0;
		Instance curr;
		while(i < k) {
			curr = neighbors.poll();
			vote.putIfAbsent(curr.realClass, 1);
			vote.computeIfPresent(curr.realClass, (key, value)->value+1);
			i++;
		}
		
		predictedClass = vote.firstKey();
		// System.out.println("My Prediction is " + predictedClass);
		
		return predictedClass;
	}
	
	public void normalize() {
		double mean, stddev, sum, sumSquared;

		for(int i = 0; i < featureNum; i++) {
			mean = 0;
			sum = 0;
			stddev = 0;
			sumSquared = 0;
			for(int j = 0; j < instanceNum; j++) {
				double element = trained.get(j).features[i];
				sum += element;
				sumSquared += Math.pow(element, 2);
			}

			mean = sum/instanceNum;
			stddev = Math.sqrt((sumSquared - Math.pow(sum, 2)/instanceNum) / (instanceNum - 1));
			// System.out.println(mean + ", " + stddev);
			for(int j = 0; j < instanceNum; j++) {
				double element = trained.get(j).features[i];
				trained.get(j).features[i] = (element - mean) / stddev;
			}
		}
	}
}
