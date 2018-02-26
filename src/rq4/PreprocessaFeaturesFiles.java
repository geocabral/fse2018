package rq4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class PreprocessaFeaturesFiles {

	
	public static void main(String[] args) throws IOException{
		
		String currentDataset = "rootsNeutron";
		FileReader in = new FileReader("RQ4/"+currentDataset+".txt");
        BufferedReader br = new BufferedReader(in);

        String[] heads = new String[14];
        ArrayList<Integer[]> data = new ArrayList<>();
        
        
        String strAux ="";
        int ct = 0;
        Integer[] dataAux = new Integer[14];
        while ((strAux = br.readLine()) != null) {
        	
        	StringTokenizer strTok = new StringTokenizer(strAux,"\t");
        	dataAux = new Integer[14];
        	if(ct == 0){
        		for(int i = 0;i < 14; i++){
        			heads[i] = strTok.nextToken();
        		}
        	}else{
        		for(int i = 0;i < 14; i++){
        			dataAux[i] = new Integer(strTok.nextToken());
        		}
        		
        		data.add(dataAux);
        	}
        	
        	ct++;
        	
        }
        
        
        //exclui colunas zeradas
        ArrayList<Integer> colunasValidas = new ArrayList<>();
        
        for(int i = 0; i < data.size(); i ++){
        	for(int j = 0; j < 14; j ++){
        		if(data.get(i)[j]>0){
        			if(!colunasValidas.contains(j)){
        				colunasValidas.add(j);
        			}
        		}
        	}
        }
        
        
        
        ArrayList<Double[]> data2 = new ArrayList<>();
        Double[] dataAux2 = null;
        for(int i = 0; i < data.size(); i ++){
        	
        	dataAux2 = new Double[colunasValidas.size()];
        	
        	int ctNovoNrCols = 0;
        	for(int j = 0; j < 14; j++){
        		if(colunasValidas.contains(j)){
        			dataAux2[ctNovoNrCols] = new Double(data.get(i)[j]);
        			ctNovoNrCols++;
        		}
        	}
        	data2.add(dataAux2);
        }
        
        //exclui heads de colunas zeradas
        String[] heads2 = new String[colunasValidas.size()];
        int ctNovoNrCols = 0;
    	for(int j = 0; j < 14; j++){
    		if(colunasValidas.contains(j)){
    			heads2[ctNovoNrCols] = heads[j];
    			ctNovoNrCols++;
    		}
    	}
    	
    	
    	//incrementa features que tem o mesmo valor ao mesmo tempo
    	incrementaFeatures(data2);
        
    	Writer writerFile = new FileWriter("RQ4/"+currentDataset+"Proc.txt");
    	for(int i = 0; i < heads2.length; i++){
    		writerFile.write(heads2[i]+"\t");
    	}
    	writerFile.write("\n");
    	
    	for(int i = 0; i < data2.size(); i++){
    		Double[] line = data2.get(i);
    		
    		for(int j = 0; j < line.length; j++){
    			writerFile.write(line[j]+"\t");
    		}
    		writerFile.write("\n");
    	}
    	
    	writerFile.close();
    	
        
        
	}

	private static void incrementaFeatures(ArrayList<Double[]> data2) {
		
		double incr = 0.02;
		HashMap<Double, Integer> hashCount = new HashMap<>();
		for(int i = 0; i < data2.size(); i++){
			
			hashCount = new HashMap<>();
			
			for(int j = 0; j < data2.get(i).length; j++){
				if(hashCount.containsKey(data2.get(i)[j])){
					hashCount.put(data2.get(i)[j], hashCount.get(data2.get(i)[j])+1);
				}else{
					hashCount.put(data2.get(i)[j], 1);
				}
			}
			
			for(int j = 0; j < data2.get(i).length; j++){
				hashCount.put(data2.get(i)[j], hashCount.get(data2.get(i)[j])-1);
				data2.get(i)[j] += (hashCount.get(data2.get(i)[j]) * incr);
			}
			
			
		}
		
		
	}
	
}
