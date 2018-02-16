package chartspart2;

import java.io.IOException;
import java.io.Writer;
import java.util.Random;

import moa.options.ClassOption;
import moa.tasks.EvaluatePrequential;
import moa.tasks.MainTask;
import moa.tasks.TaskThread;

public class ExperimentDifferentParametersOzaBag {

	static MainTask currentTask = new EvaluatePrequential();
	static Writer writer; 
	
	public ExperimentDifferentParametersOzaBag(){
		
	}
	
	public static void main(String[] args) throws IOException {
		
		int[] ensembleSizes = {10}; 
		double[] fadingFactors = {0.99};
		
		String[] datasets = {"brackets.arff","jgroups.arff","camel.arff","neutron.arff","tomcat.arff"};
		
		
		String cls = "WFLOzaBag";
	    
	    for(int dt = 0; dt < datasets.length; dt++){
	    	
	    	for(int i = 0; i < ensembleSizes.length; i++){
				for(int j = 0; j < fadingFactors.length; j++){
					
					for(int r = 0; r < 5; r++){
			
						//writer = new FileWriter("parametersSettings/"+dataset+i+"-"+j+cls+".txt");
						
						String task = "EvaluatePrequential -l (meta.WaitForLabelsOzaBag -s "+ensembleSizes[i]+")  -s  (ArffFileStream -f (/Users/georgegomescabral/ProjetosSoftwares/Java/workspace/PaperMarch2018/arffs/"+datasets[dt]+") -c 15) -e (FadingFactorEachClassPerformanceEvaluator -a "+fadingFactors[j]+") -f 1 -d /Users/georgegomescabral/ProjetosSoftwares/Java/workspace/PaperMarch2018/OzaBagResPart2/"+datasets[dt]+"("+ensembleSizes[i]+"-"+fadingFactors[j]+")"+cls+r+".csv";
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
