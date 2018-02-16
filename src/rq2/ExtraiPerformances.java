package rq2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ExtraiPerformances {

	public static void main(String args[]) {

		int[] ensembleSizes = { 10, 30, 50 };
		double[] fadingFactors = { 0.999, 0.99, 0.9 };
		int[] treeSizes = { 2, 4, 6 };

		String dataset = "fabric.arff";
		String cls = "WFLARF";
		for (int i = 0; i < ensembleSizes.length; i++) {
			for (int j = 0; j < fadingFactors.length; j++) {
				for (int m = 0; m < treeSizes.length; m++) {
					Double recall0Med = 0.0;
					Double recall1Med = 0.0;
					Double gmeanMed = 0.0;

					for (int s = 0; s < 5; s++) {

						BufferedReader br = null;
						try {
							br = new BufferedReader(new FileReader("paramSettingsEval/" + dataset + "("
									+ ensembleSizes[i] + "-" + fadingFactors[j] +"-" + treeSizes[m] + ")" + cls + s + ".csv"));
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
								if (ct != 0) {
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
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					System.out.println(recall0Med / 5 + "\t" + recall1Med / 5 + "\t" + gmeanMed / 5);

				}
			}

		}
	}

}
