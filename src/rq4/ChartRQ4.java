package rq4;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ChartRQ4  extends JFrame {

	XYDataset xydataset;
	
    /**
     * Constructs a new demonstration application.
     *
     * @param title  the frame title.
     */
    public ChartRQ4(final String title) {

        super(title);

        try {
			xydataset = createDataset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
            "Boys VS Girls weight comparison chart", 
            "X-Axis", "Y-Axis", xydataset);

        
        //Changes background color
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(255,228,196));
        XYItemRenderer r = plot.getRenderer();
        r.setSeriesPaint(0, Color.GREEN);
        r.setSeriesPaint(1, Color.RED);
        
       
        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);        

    }
    
    
    private XYDataset createDataset() throws IOException{
		
        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries series1 = new XYSeries("Non Defect");
        XYSeries series2 = new XYSeries("Defect");
        
    	FileReader in = new FileReader("RQ4/output10000.txt");
        BufferedReader br = new BufferedReader(in);

        
        String strAux ="";
        while ((strAux = br.readLine()) != null) {
            StringTokenizer strTok = new StringTokenizer(strAux, "\t");
            double x = new Double(strTok.nextToken());
            double y = new Double(strTok.nextToken());
            strTok.nextToken();
            strTok.nextToken();
            double cls = new Double(strTok.nextToken());
            
            if(cls == 1){
            	series2.add(x, y);
            }else{
            	series1.add(x, y);
            }
            
        }
        in.close();
		
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        
        return dataset;
	}


	public static void main(final String[] args) {

    	ChartRQ4 example = new ChartRQ4("teste");
    	example.setSize(800, 400);
        example.setLocationRelativeTo(null);
        example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        example.setVisible(true);

    }

}