package rq3;

import java.io.IOException;
import java.io.Writer;
import java.util.Random;

import moa.options.ClassOption;
import moa.tasks.EvaluatePrequential;
import moa.tasks.MainTask;
import moa.tasks.TaskThread;

public class ExperimentBestParametersFixedClassRatioOOB {

	static MainTask currentTask = new EvaluatePrequential();
	static Writer writer; 
	
	public ExperimentBestParametersFixedClassRatioOOB(){
		
	}
	
	public static void main(String[] args) throws IOException {
		
		int[] ensembleSizes = {10,10,10,30,50,10}; 
		double[] theta = {0.99,0.999,0.9,0.99,0.9,0.99};
		double fadingFactor = 0.99;
		String cls = "OOB";
	    
		String[] datasets = {"fabric","camel","brackets","tomcat","jgroups", "neutron"};
		
		String pathWindows = "C:/ProjetosSoftware/fse2018/";
		String pathMac = "/Users/georgegomescabral/ProjetosSoftwares/Java/workspace/fse2018/";
		
		boolean isWindows = true;
		String path = "";
		
		if(isWindows) {
			path = pathWindows;
		}else {
			path = pathMac;
		}
		
		for(int d = 0; d < datasets.length; d++){
					for(int r = 0; r < 30; r++){
		
						
						//writer = new FileWriter("parametersSettings/"+dataset+i+"-"+j+cls+".txt");
						
						String task = "EvaluatePrequential -l (meta.WaitForLabelsOOB -s " + ensembleSizes[d]
								+ " -t "+theta[d]+")  -s  (ArffFileStream -f ("+path+"arffs/"
								+ datasets[d] + ".arff) -c 15) -e (FadingFactorEachClassPerformanceEvaluator -a "
								+ fadingFactor
								+ ") -f 1 -d "+path+"RQ3/BestParamExp/oob/"+datasets[d]+"/"
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
		
		
	
	
}
