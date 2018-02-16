package chartspart4;

import java.io.IOException;
import java.io.Writer;
import java.util.Random;

import moa.options.ClassOption;
import moa.tasks.EvaluatePrequential;
import moa.tasks.MainTask;
import moa.tasks.TaskThread;

public class Experiment{

	static MainTask currentTask = new EvaluatePrequential();
	static Writer writer; 
	
	public Experiment(){
		
	}
	
	public static void main(String[] args) throws IOException {
		
		String dataset = "fabric.arff";
		
		
		//writer = new FileWriter("parametersSettings/"+dataset+i+"-"+j+cls+".txt");
		
		String task = "EvaluatePrequential -l (meta.UOB -s 10 )  -s  (ArffFileStream -f (/Users/georgegomescabral/ProjetosSoftwares/Java/workspace/PaperMarch2018/arffs/"+dataset+") -c 15) -e (FadingFactorEachClassPerformanceEvaluator -a 0.9) -f 1 -d /Users/georgegomescabral/ProjetosSoftwares/Java/workspace/PaperMarch2018/exps4/"+dataset+".csv";
		System.out.println(task);
		try {
            currentTask = (MainTask) ClassOption.cliStringToObject(
                    task, MainTask.class, null);
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
		
		TaskThread thread = new TaskThread((moa.tasks.Task) currentTask);

		thread.start();

	    
		
		
		
	}
	
}
