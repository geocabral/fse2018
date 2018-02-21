package rq3;

import java.io.IOException;
import java.io.Writer;
import java.util.Random;

import moa.options.ClassOption;
import moa.tasks.EvaluatePrequential;
import moa.tasks.MainTask;
import moa.tasks.TaskThread;

public class ExperimentDifferentParametersUOB {

	static MainTask currentTask = new EvaluatePrequential();
	static Writer writer; 
	
	public ExperimentDifferentParametersUOB(){
		
	}
	
	public static void main(String[] args) throws IOException {
		
		int[] ensembleSizes = {10, 30, 50}; 
		
		
		String cls = "UOB";
	    Random randomGenerator = new Random();
		String[] datasets = {"fabric","camel","brackets","tomcat","jgroups", "neutron"};
		double[] theta = {0.999,0.99,0.9};
		
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
			for(int i = 0; i < ensembleSizes.length; i++){
				for(int j = 0; j < theta.length; j++){
					
					for(int r = 0; r < 5; r++){
		
						int randomInt = randomGenerator.nextInt(1000);					

						//writer = new FileWriter("parametersSettings/"+dataset+i+"-"+j+cls+".txt");
						
						String task = "EvaluatePrequential -i 2500 -l (meta.WaitForLabelsUOB -s " + ensembleSizes[i]
								+ " -t "+theta[j]+")  -s  (ArffFileStream -f ("+path+"arffs/"
								+ datasets[d] + ".arff) -c 15) -e (FadingFactorEachClassPerformanceEvaluator -a 0.99)"
										+ " -f 1 -d "+path+"RQ3/ParamEval/uob/"+datasets[d]+"/"
								+ datasets[d] + "(" + ensembleSizes[i] + "-" + theta[j] + ")" + cls + r + ".csv";
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
		
		
		//String task = "EvaluatePrequential -l (meta.WaitForLabelsUOB  -t 0.99)  -s (ArffFileStream -f (/Users/georgegomescabral/ProjetosSoftwares/Java/workspace/MOA/datasets/jruby_paper_attributes_plus_timestamp.arff) -c 15) -e (FadingFactorEachClassPerformanceEvaluator -a 0.99) -f 1 -d /Users/georgegomescabral/ProjetosSoftwares/Java/workspace/MOA/results/jruby-ARF.csv";
		
		
	}
	
}
