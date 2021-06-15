All the source codes are in src folder.
Run main.java, which is stored in the current directory.
This project is run with Eclipse IDE(Windows) and JDK-14.0.1
Under linux environments, the program tested with openjdk-11. Run the following commandst to compile and run:
	javac Main.java
	java Main

Alternatively, you can run "make" to compile and run Main.java
"make clean" will clear all .class files in this directory

Then, the program will ask for inputs to run a greedy search with randomized features.
	First, the user selects which search algorithm to run.(But since we are not doing feature search, it will do nothing)
		1. Forward Selection: starts with empty, adds a feature as it traverses
		2. Backward Elimination: starts with all features, removes a feature as it traverses
	Second, the user will choose the dataset.
		1. Small dataset with 100 instances and 10 features
		2. Large dataset with 1000 instances and 40 features
		3. You can input custom dataset(format has to be consistent with given sets) if the file exists inside Resources directory
	Third, the user will input the subset of features to test with.
		Numbers separated with a whitespace.
		Default subset is {3 5 7}
	Process of search will be printed.
		Since printing inside the actual function slows down the program, the program will not print what happens inside the function
		Though it will print time elapsed in each method: train(), normalize(), and test().
	The result includes:
		1. Time elapsed while running train(), normalize(), and test() in millisecond
		2. Subset of features
		3. Accuracy of given subset
		4. Size of dataset
	
