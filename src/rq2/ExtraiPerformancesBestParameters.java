package rq2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;

public class ExtraiPerformancesBestParameters {

	public static void main(String args[]) {

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
			
			Double recall0Med = 0.0;
			Double recall1Med = 0.0;
			Double gmeanMed = 0.0;
					for(int r = 0; r < 30; r++){
						

					
					
					
						BufferedReader br = null;
						try {
							br = new BufferedReader(
									new FileReader("RQ2/BestParamExp/" + datasets[d] +"/"+datasets[d]+ "(" + ensembleSizes[d] + "-"
											+ fadingFactor + ")" + cls + r + ".csv"));
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
					System.out.println(datasets[d]+"\t"+recall0Med / 30 + "\t" + recall1Med / 30 + "\t" + gmeanMed / 30);

				}
			}
		

	}

