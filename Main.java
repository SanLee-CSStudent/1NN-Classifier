import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Algorithms.Algorithm;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		
		System.out.println("Welcome to San Lee's Feature Selection Algorithm.");
		System.out.println("Type in the name of the file to test: ");
		// Add Scanner object, get input text file from the user
		System.out.println("Currently there is no data set.\n");
		
		System.out.println("1. Forward Selection\n2. Backward Elimination\n"
				+ "Type the number of the algorithm you want to run: ");
		// Use Scanner object to get input number from the user
		// With input number, create corresponding algorithm object
		int algorithmNum = 0;
		algorithmNum = in.nextInt();
		in.nextLine();
		// System.out.println("Enter the total number of features: ");
		// Use Scanner object to get number of features
		// int featureNum = 0;
		// featureNum = in.nextInt();
		/*try {
			featureNum = in.nextInt();
		}
		catch(InputMismatchException e) {
			System.out.println("Invalid input. Terminating...");
			System.exit(0);
		}*/
		
		System.out.println("Select the dataset\nOr input the name of dataset in Resources directory.\n1. Small\n2. Large");
		String setSize = in.nextLine();
		ArrayList<String> s = null;
		setSize.trim();
		if(setSize.equals("1")) {
			s = loadData("cs_170_small80.txt");
		}
		else if(setSize.equals("2")) {
			s = loadData("cs_170_large80.txt");
		}
		else {
			s = loadData(setSize);
		}
		
		System.out.println("Input the subset of features to test with.\n(Seprated with whitespace, default: 3 5 7)");
		String subset = in.nextLine();
		if(subset.equals("")) {
			subset = "3 5 7";
		}
		String[] fs = subset.split(" ");
		int[] features = new int[fs.length];
		for(int i = 0; i < fs.length; i++) {
			features[i] = Integer.parseInt(fs[i]);
		}
		// NearestNeighbor NN = new NearestNeighbor(s);
		
		Algorithm problem = new Algorithm(s, features);
		 
		// problem.run(algorithmNum);
	}
	
	public static ArrayList<String> loadData(String filename) {
		String dir = "Resources/";
		BufferedReader fin;
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
			fin = new BufferedReader(new FileReader(dir + filename));
			String line = fin.readLine();
			while(line != null) {
				lines.add(line);
				line = fin.readLine();
			}
			fin.close();
		}
		catch(IOException e) {
			System.out.println(filename + " not found. Terminating...");
			System.exit(0);
		}
		
		return	lines;
	}
}
