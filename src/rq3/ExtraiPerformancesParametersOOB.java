package rq3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;

public class ExtraiPerformancesParametersOOB {

	public static void main(String args[]) {

		int[] ensembleSizes = {10, 30, 50}; 
		String cls = "OOB";
	    Random randomGenerator = new Random();
		String[] datasets = {"fabric","camel","brackets","tomcat","jgroups", "neutron"};
		double[] theta = {0.999,0.99,0.9};

		String pathWindows = "C:/ProjetosSoftware/fse2018/";
		String pathMac = "/Users/georgegomescabral/ProjetosSoftwares/Java/workspace/fse2018/";

		boolean isWindows = true;
		String path = "";
		if (isWindows) {
			path = pathWindows;
		} else {
			path = pathMac;
		}

		for(int d = 0; d < datasets.length; d++){
			for(int i = 0; i < ensembleSizes.length; i++){
				for(int j = 0; j < theta.length; j++){
					
					Double recall0Med = 0.0;
					Double recall1Med = 0.0;
					Double gmeanMed = 0.0;
					
					for (int r = 0; r < 5; r++) {

						

						BufferedReader br = null;
						try {
							br = new BufferedReader(
									new FileReader("RQ3/ParamEval/oob/" + datasets[d] +"/"+datasets[d]+ "(" + ensembleSizes[i] + "-"
											+ theta[j] + ")" + cls + r + ".csv"));
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						StringTokenizer strTok = null;

						Double recall0 = 0.0;
						Double recall1 = 0.0;
						Double gmean = 0.0;

						String strAux = "";
						int ct = 0;
						try {

							
							while ((strAux = br.readLine()) != null) {
								
								if (ct > 0) {
									
									//System.out.println(ct);
									strTok = new StringTokenizer(strAux, ",");

									strTok.nextToken();
									strTok.nextToken();
									strTok.nextToken();
									strTok.nextToken();
									strTok.nextToken();
									strTok.nextToken();
									strTok.nextToken();
									strTok.nextToken();
									strTok.nextToken();
									strTok.nextToken();
									recall0 += new Double(strTok.nextToken());
									recall1 += new Double(strTok.nextToken());

								}
								ct++;
							}

							recall0 = recall0 / ct;
							recall1 = recall1 / ct;
							gmean = Math.sqrt(recall0 * recall1);

							recall0Med += recall0;
							recall1Med += recall1;
							gmeanMed += gmean;
							
							ct = 0;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ct = 0;
					}
					System.out.println(cls+"("+ensembleSizes[i]+" - "+theta[j]+")\t"+datasets[d]+"\t"+recall0Med / 5 + "\t" + recall1Med / 5 + "\t" + gmeanMed / 5);

				}
			}
		}

	}

}
