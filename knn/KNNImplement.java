package knn;
import java.io.File;
import java.util.ArrayList;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class KNNImplement {
	public static final String TRAINING_DATA_SET_FILENAME = "D://eclipse-workspace/CompositePhraseGenrator/WebContent/knn/wordvect.arff";

	public static Instances getDataSet(String fileName) throws Exception {

		int classIdx = 50;
		ArffLoader loader = new ArffLoader();

		loader.setFile(new File(fileName));
		Instances dataSet = loader.getDataSet();
		dataSet.setClassIndex(classIdx);
		return dataSet;

	}

	/**
	 * This method is used to process the input and return the statistics.
	 * 
	 * @throws Exception
	 */
	public static void process() throws Exception {

		Instances trainingDataSet = getDataSet(TRAINING_DATA_SET_FILENAME);
		Instances testingDataSet = getDataSet(TRAINING_DATA_SET_FILENAME);
		Instances predictingDataSet = getDataSet(TRAINING_DATA_SET_FILENAME);
		/** Classifier here is Linear Regression */
		Classifier classifier = new IBk();
		/** */
		classifier.buildClassifier(trainingDataSet);

		Evaluation eval = new Evaluation(trainingDataSet);
		eval.evaluateModel(classifier, testingDataSet);

		/** Print the algorithm summary */
		System.out.println("** KNN Evaluation with Datasets **");
		System.out.println(eval.toSummaryString());
		System.out.print(" the expression for the input data as per alogorithm is ");
		System.out.println(classifier);
		for (int i = 0; i < predictingDataSet.numInstances(); i++) {
			System.out.println(predictingDataSet.instance(i));
			double index = classifier.classifyInstance(predictingDataSet.instance(i));
			String className = trainingDataSet.attribute(50).value((int) index);
			System.out.println(className);
		}
		// System.out.println(trainingDataSet);
		ArrayList<Attribute> attributes;
		ArrayList<String> classVal;
		Instances dataRaw;
		attributes = new ArrayList<Attribute>();
		for (int i = 1; i <= 50; i++) {
			Attribute vect = new Attribute("vect" + i);
			attributes.add(vect);
		}

		classVal = new ArrayList<String>();
		classVal.add("food");
		classVal.add("service");
		classVal.add("ambience");
		classVal.add("price");
		classVal.add("misc");
		attributes.add(new Attribute("class", classVal));
		dataRaw = new Instances("TestInstances", attributes, 0);
		dataRaw.setClassIndex(dataRaw.numAttributes() - 1);

		dataRaw.add(new DenseInstance(1.0,
				new double[] { 0.89, 0.0022248, 1.0093, -0.55798, -0.28476, 0.30152, -0.15118, -0.76853, 0.44135,
						0.22737, -0.45814, -0.19772, 0.10353, -0.89961, 1.0729, 0.10177, 0.15114, 0.59041, -0.35506,
						-0.87802, 0.78812, -0.85243, -0.74337, -0.7119, -0.26325, -1.1461, -0.042493, -0.28891, 0.65035,
						0.45462, 3.4999, 0.81029, 0.28177, 0.36095, 0.1722, 0.050072, 0.2849, 0.61834, 0.35741,
						-0.47174, -0.045342, 0.054026, 0.67601, 0.63232, -0.6344, -0.093767, -0.19312, 0.71263,
						-0.15523, 0.61625, 0 }));
		double index = classifier.classifyInstance(dataRaw.get(0));
		String className = trainingDataSet.attribute(50).value((int) index);
		System.out.println("Class" + className);

	}

	public static void main(String args[]) {
		try {
			process();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

