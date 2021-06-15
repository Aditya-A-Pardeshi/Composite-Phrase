package knn;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class ModelClassifier {

    private ArrayList<Attribute> attributes;
    private ArrayList<String> classVal;
    private Instances dataRaw;


    public ModelClassifier() {
    	attributes = new ArrayList<Attribute>();
    	for(int i=1;i<=50;i++) {
    		Attribute vect=new Attribute("vect"+i);
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
    }

    
    public Instances createInstance(double[] vect,  double result) {
        dataRaw.clear();
//        double[] instanceValue1 = new double[]{vect };
        dataRaw.add(new DenseInstance(1.0, vect));
        return dataRaw;
    }


    public String classifiy(Instances insts, String path) {
        String result = "Not classified!!";
        Classifier cls = null;
        try {
            cls = (MultilayerPerceptron) SerializationHelper.read(path);
            result = (String) classVal.get((int) cls.classifyInstance(insts.firstInstance()));
        } catch (Exception ex) {
            Logger.getLogger(ModelClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }


    public Instances getInstance() {
        return dataRaw;
    }
    

}
