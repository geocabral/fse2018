import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ConverteCVSSoftwareARF {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		processaArquivo("tomcat.txt");
	}
	
	
	public static void processaArquivo(String _arquivo){
		String thisLine = null;
	      
	      try {
	      
	    	  Writer writer = new FileWriter("arffs/tomcat.arff");

	    	  
	    	  
	    	  
	         // open input stream test.txt for reading purpose.
	         BufferedReader br = new BufferedReader(new FileReader(_arquivo));
	         
	         StringTokenizer strTok = null;
	         
	         
	         String timestamp = null;
	         String fix = null;
	         String containsBug = null;
	         String ns = null;
	         String nd = null;
	         String nf = null;
	         String entrophy = null;
	         String la = null;
	         String ld = null;
	         String lt = null;
	         String ndev = null;
	         String age = null;
	         String nuc = null;
	         String exp = null;
	         String rexp = null;
	         String sexp = null;
	         
	         HashMap<String, String> hashTimeStamps = new HashMap<>();
	         boolean skipLine = false;
	         while ((thisLine = br.readLine()) != null) {
	            
	        	 //reseta as features
	        	 timestamp = "";
		         fix = "";
		         containsBug = "";
		         ns = "";
		         nd = "";
		         nf = "";
		         entrophy = "";
		         la = "";
		         ld = "";
		         lt = "";
		         ndev = "";
		         age = "";
		         nuc = "";
		         exp = "";
		         rexp = "";
		         sexp = "";
	        	 
	        	 skipLine = false;
	        	 
	        	 strTok = new StringTokenizer(thisLine,"\t");
	        	 
	        	 if(strTok.countTokens() < 16)
	        		 skipLine = true;
	        	 
	        	 
	        	 try{
	        		 
	        		 timestamp = strTok.nextToken().trim();
	        		 if(hashTimeStamps.containsKey(timestamp) == true){
	        			 skipLine = true;
	        		 }else{
	        			 hashTimeStamps.put(timestamp, timestamp);
	        		 }
	        		 
	        		 Date time = new Date(new Long(timestamp)*1000l);

	        	 }catch(Exception e){
	        		 skipLine = true;
	        	 }
	        	 
	        	 if(!skipLine){
	        		 fix = strTok.nextToken().trim();
		        	 containsBug = strTok.nextToken().trim();
		        	 ns = strTok.nextToken().trim();
		        	 nd = strTok.nextToken().trim();
		        	 nf = strTok.nextToken().trim();
		        	 entrophy = strTok.nextToken().trim();
		        	 la = strTok.nextToken().trim();
		        	 ld = strTok.nextToken().trim();
		        	 lt = strTok.nextToken().trim();
		        	 ndev = strTok.nextToken().trim();
		        	 age = strTok.nextToken().trim();
		        	 nuc = strTok.nextToken().trim();
		        	 exp = strTok.nextToken().trim();
		        	 rexp = strTok.nextToken().trim();
		        	 sexp = strTok.nextToken().trim();
		        	 
		        	 
		        	 String example = fix+","+ ns+","+nf+","+ nf+","+entrophy+","+ la+","+ld+","+ lt+","+
		        			 ndev+","+ age+","+ nuc+","+ exp+","+ rexp+","+ sexp+","+containsBug+","+ timestamp;
		        	 
//		        	 String example = fix+"\t"+ ns+"\t "+nf+"\t"+ nf+"\t "+entrophy+"\t"+ la+"\t "+ld+"\t"+ lt+"\t "+
//		        			 ndev+"\t"+ age+"\t "+ nuc+"\t"+ exp+"\t "+ rexp+"\t"+ sexp+"\t "+containsBug+"\t"+ timestamp;
		        	 
		        	 if(!sexp.equals(""))
		        		 writer.write(example+"\n");
		        	 
		        	 
		        	 
		        	// System.out.println(example);	 
	        	 }
	        	 
	         }
	         
	         writer.close();
	         
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	}

}
