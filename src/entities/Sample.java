package entities;

import java.util.ArrayList;

public class Sample {

	
	ArrayList<String> features;
	String classValue;
	
	
	public Sample(ArrayList<String> features, int classIndex) {
		super();
		this.features = features;
		classValue = this.features.get(classIndex);
	}
	
	
	public ArrayList<String> getFeatures() {
		return features;
	}
	public void setFeatures(ArrayList<String> features) {
		this.features = features;
	}
	public String getClassValue() {
		return classValue;
	}
	public void setClassValue(String classValue) {
		this.classValue = classValue;
	}
	
	
}
