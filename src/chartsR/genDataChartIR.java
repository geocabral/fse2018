package chartsR;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.yahoo.labs.samoa.instances.Instance;

import moa.streams.ArffFileStream;

public class genDataChartIR {

	public static void main(String[] args) {

		int[] datasetsSizes = {17285,30511,12782,10697,19372, 18904};
		String[] datasetsNames = { "Brackets", "Camel", "Fabric", "JGroups", "Neutron", "Tomcat" };

		String pathRoot = "/Users/georgegomescabral/ProjetosSoftwares/Java/workspace/fse2018/";
		String pathCurrentTask = "chartsR/data/RQ1/";
		
		for (int i = 0; i < 6; i++) {
			ArffFileStream streamTe = new ArffFileStream(pathRoot+"arffs/"+datasetsNames[i].toLowerCase()+".arff", 15);
			streamTe.prepareForUse();
			
			
			FileWriter fw = null;
			int ctDefect = 0;
			int ctNonDefect = 0;
			try {
				fw = new FileWriter(new File(pathRoot+pathCurrentTask+datasetsNames[i]+"IR.txt"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				fw.write("Non-defec\tDefect\n");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			while (streamTe.hasMoreInstances()) {
				Instance inst = streamTe.nextInstance().getData();
				if(inst.classValue() == 0.0){
					ctNonDefect++;
				}else{
					ctDefect++;
				}
				try {
					fw.write((new Double(ctNonDefect)/datasetsSizes[i]) +"\t" +(new Double(ctDefect)/datasetsSizes[i])+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
			
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		

		

		int numberSamples = 0;

		

	}

}
