package Classifier;

/*
	Nearest Neighbor classifier wrapping class
		Assume we save up some test instances from the original data.
		Fields:
			public data --> list of Instances(String[] at first, Instance[] later)
				Split into Training/Testing sets
			public train/test sets
			featureNum
		Methods:
			Constructor(filename, fraction): maybe k(default = 1)
				call loadData()
				Separate data into train/test sets by fraction
				call train(train set)
				call test(test set)
			Train()
				Input: list of data
				Given list of data(preferrably be class feature0 feature1 feature2...),
				create an instance(label, features)
				store it to train set
			Test()
				Compute Euclidean distance between a test point and points in data(which will be used in validator class)
				Store distance(that in form of Instance object) in priority queue(use lambda comparator)
				Choose first k-th instances from the priority queue
				Vote and return the label of maximum classes
			LoadData()
				Load data from text file
				assign featureNum
				Z-Normalization: data - mean / stddev
				Separate data into train/test set depending on parameter
			mean()
				given a set of data from one feature
				compute and return mean
			stddev()
				given a set of data from one feature
				compute E|X^2|
				return E|x^2| - (E|x|)^2 / n-1
	Instance class
		Fields: Label, float[] features: instant access by featureNum, distance
*/