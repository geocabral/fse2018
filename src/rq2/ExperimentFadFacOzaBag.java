package rq2;

import java.io.IOException;
import java.io.Writer;
import java.util.Random;

import moa.options.ClassOption;
import moa.tasks.EvaluatePrequential;
import moa.tasks.MainTask;
import moa.tasks.TaskThread;

public class ExperimentFadFacOzaBag {

	static MainTask currentTask = new EvaluatePrequential();
	static Writer writer; 
	
	public ExperimentFadFacOzaBag(){
		
	}
	
	public static void main(String[] args) throws IOException {
		
		int[] ensembleSizes = {10}; 
		double[] fadingFactors = {0.999, 0.99, 0.9};
		String cls = "WFLOzaBag";
	    
		String[] datasets = {"brackets"};
		
		String pathWindows = "C:/ProjetosSoftware/fse2018/";
		String pathMac = "/Users/georgegomescabral/ProjetosSoftwares/Java/workspace/fse2018/";
		
		boolean isWindows = true;
		String path = "";
		
		if(isWindows) {
			path = pathWindows;
		}else {
			path = pathMac;
		}
		
		for(int d = 0; d < fadingFactors.length; d++){
					for(int r = 0; r < 1; r++){
		
						
						//writer = new FileWriter("parametersSettings/"+dataset+i+"-"+j+cls+".txt");
						
						String task = "EvaluatePrequential -l (meta.WaitForLabelsOzaBag -s " + ensembleSizes[0]
								+ ")  -s  (ArffFileStream -f ("+path+"arffs/"
								+ datasets[0] + ".arff) -c 15) -e (FadingFactorEachClassPerformanceEvaluator -a "
								+ fadingFactors[d]
								+ ") -f 1 -d "+path+"RQ2/fadfac/"
								+ datasets[0] + "(" + ensembleSizes[0] + "-" + fadingFactors[d] + ")" + cls + r + ".csv";
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
