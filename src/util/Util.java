package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import entities.Sample;

public class Util {

	
	public static Vector<Sample> openDsArff(String file) throws IOException{
		
		Vector<Sample> vetRetorno = new Vector<>();
		
		BufferedReader in = new BufferedReader(new FileReader(file));
		StringTokenizer strTok = null;

		String strAux = "";

		boolean start = false;
		ArrayList<String> features;
		while ((strAux = in.readLine()) != null) {
			
			
			if(strAux.trim().equals("@data")){
				start = true;
			}else{
				features = new ArrayList<String>();
				if(start == true){
					strTok = new StringTokenizer(strAux,",");
					
					while (strTok.hasMoreTokens()) {
						features.add(strTok.nextToken());
					}
					
					vetRetorno.addElement(new Sample(features, 14));
					
				}	
			}

		}
		
		in.close();
		
		return vetRetorno;
	}
	
}
