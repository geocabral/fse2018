package rq2;

import java.io.IOException;
import java.io.Writer;
import java.util.Random;

import moa.options.ClassOption;
import moa.tasks.EvaluatePrequential;
import moa.tasks.MainTask;
import moa.tasks.TaskThread;

public class ExperimentBestParametersOzaBag {

	static MainTask currentTask = new EvaluatePrequential();
	static Writer writer; 
	
	public ExperimentBestParametersOzaBag(){
		
	}
	
	public static void main(String[] args) throws IOException {
		
		int[] ensembleSizes = {30, 10, 10, 30, 50 , 30}; 
		double fadingFactor = 0.99;
		String cls = "WFLOzaBag";
	    
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
						
						String task = "EvaluatePrequential -l (meta.WaitForLabelsOzaBag -s " + ensembleSizes[d]
								+ ")  -s  (ArffFileStream -f ("+path+"arffs/"
								+ datasets[d] + ".arff) -c 15) -e (FadingFactorEachClassPerformanceEvaluator -a "
								+ fadingFactor
								+ ") -f 1 -d "+path+"RQ2/BestParamExp/"+datasets[d]+"/"
								+ datasets[d] + "(" + ensembleSizes[d] + "-" + fadingFactor + ")" + cls + r + ".csv";
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
