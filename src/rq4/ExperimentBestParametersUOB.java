package rq4;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import moa.classifiers.meta.WaitForLabelsUOB;
import moa.options.ClassOption;
import moa.tasks.EvaluatePrequential;
import moa.tasks.MainTask;
import moa.tasks.TaskThread;

public class ExperimentBestParametersUOB {

	static MainTask currentTask = new EvaluatePrequential();
	static Writer writer;

	public ExperimentBestParametersUOB() {

	}

	public static synchronized void main(String[] args) throws IOException {

		int[] ensembleSizes = { 10 };
		double[] theta = { 0.9 };
		double fadingFactor = 0.99;
		String cls = "UOB";

		String[] datasets = { "brackets" };

		String pathWindows = "C:/ProjetosSoftware/fse2018/";
		String pathMac = "/Users/georgegomescabral/ProjetosSoftwares/Java/workspace/fse2018/";

		boolean isWindows = false;
		String path = "";

		if (isWindows) {
			path = pathWindows;
		} else {
			path = pathMac;
		}

		TaskThread thread = null;

		System.out.println("roots");
		for (int i = 0; i < 14; i++) {
			System.out.print("(" + i + "-" + WaitForLabelsUOB.rootAtt[i] + ")\t");

		}
		System.out.println("\n");

		System.out.println("sub roots");
		for (int i = 0; i < 14; i++) {
			System.out.print("(" + i + "-" + WaitForLabelsUOB.subRootAtt[i] + ")\t");

		}
		System.out.println("\n");

		for (int d = 0; d < datasets.length; d++) {
			for (int r = 0; r < 1; r++) {

				// writer = new
				// FileWriter("parametersSettings/"+dataset+i+"-"+j+cls+".txt");

				String task = "EvaluatePrequential  -l (meta.WaitForLabelsUOB -s " + ensembleSizes[d] + " -t "
						+ theta[d] + ")  -s  (ArffFileStream -f (" + path + "arffs/" + datasets[d]
						+ ".arff) -c 15) -e (FadingFactorEachClassPerformanceEvaluator -a " + fadingFactor
						+ ") -f 1 -d " + path + "RQ4/" + datasets[d] + "(" + ensembleSizes[d] + "-" + theta[d] + ")"
						+ cls + r + ".csv";
				System.out.println(task);
				try {
					currentTask = (MainTask) ClassOption.cliStringToObject(task, MainTask.class, null);
					String taskText = currentTask.getCLICreationString(MainTask.class);

				} catch (Exception ex) {
					ex.printStackTrace();
				}

				thread = new TaskThread((moa.tasks.Task) currentTask);
				
				thread.start();
				

				ThreadDemo t = new ThreadDemo("", thread);
				t.start();
					
					
					


			}

		}

	}

}

class ThreadDemo extends Thread {
	private Thread t;
	private String threadName;
	TaskThread t2;
	
	
	ThreadDemo(String name, TaskThread th) {
		threadName = name;
		t2 = th;
	}

	public synchronized void run() {

		
			while(true){
				if(!t2.isAlive()){
					System.out.println("roots");
					for (int i = 0; i < 14; i++) {
						System.out.print("(" + i + "-" + WaitForLabelsUOB.rootAtt[i] + ")\t");
					}
					System.out.println("\n");

					System.out.println("sub roots");
					for (int i = 0; i < 14; i++) {
						System.out.print("(" + i + "-" + WaitForLabelsUOB.subRootAtt[i] + ")\t");
					}
					System.out.println("\n");
					break;
				}
				
			}
			
		
		


	}

	public void start() {

		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}
