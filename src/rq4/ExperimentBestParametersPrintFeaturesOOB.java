package rq4;

import java.io.IOException;
import java.io.Writer;
import java.util.Random;

import moa.options.ClassOption;
import moa.tasks.EvaluatePrequential;
import moa.tasks.MainTask;
import moa.tasks.TaskThread;

public class ExperimentBestParametersPrintFeaturesOOB {


	
	static MainTask currentTask = new EvaluatePrequential();
	static Writer writer; 
	
	public ExperimentBestParametersPrintFeaturesOOB(){
		
	}
	
	public static void main(String[] args) throws IOException {
		
		int[] ensembleSizes = {10,10,10,30,50,10}; 
		double[] theta = {0.99,0.999,0.9,0.99,0.9,0.99};
		double fadingFactor = 0.99;
		String cls = "OOB";
	    String dataset = "";
		
		String[] datasets = {"fabric","camel","brackets","tomcat","jgroups", "neutron"};
		
		String pathWindows = "C:/ProjetosSoftware/fse2018/";
		String pathMac = "/Users/georgegomescabral/ProjetosSoftwares/Java/workspace/fse2018/";
		
		boolean isWindows = false;
		String path = "";
		
		if(isWindows) {
			path = pathWindows;
		}else {
			path = pathMac;
		}
		
		int r = 0;
		for(int d = 5; d < 6; d++){
					
						dataset = datasets[d];
						//writer = new FileWriter("parametersSettings/"+dataset+i+"-"+j+cls+".txt");
						
						String task = "EvaluatePrequential -l (meta.WaitForLabelsOOB -s " + ensembleSizes[d]
								+ " -t "+theta[d]+")  -s  (ArffFileStream -f ("+path+"arffs/"
								+ datasets[d] + ".arff) -c 15) -e (FadingFactorEachClassPerformanceEvaluator -a "
								+ fadingFactor
								+ ") -f 1 -d "+path+"RQ4/"
								+ datasets[d] + "(" + ensembleSizes[d] + "-" + theta[d] + ")" + cls + r + ".csv";
						System.out.println(task);
						try {
				            currentTask = (MainTask) ClassOption.cliStringToObject(
				                    task, MainTask.class, null);
				            String taskText = currentTask.getCLICreationString(MainTask.class);

				        } catch (Exception ex) {
				            ex.printStackTrace();
				        }
						
						
						TaskThread thread = new TaskThread((moa.tasks.Task) currentTask);

						thread.start();
						
						
					
					

			}	
		}
		
		
	
	
}
