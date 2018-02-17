package chartspart2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import demo.DemoPanel;
import entities.Sample;
import util.Util;

public class ChartParameterEval extends ApplicationFrame {

    /**
     * Constructs a new demonstration application.
     *
     * @param title  the frame title.
     */
    public ChartParameterEval(final String title) {

        super(title);
        setContentPane(createDemoPanel());

        
        

    }
    
    private void setChartStyle(XYPlot plot){
    	plot.setBackgroundPaint(Color.white);
    	plot.setRangeGridlinePaint(Color.black);
    	plot.setDomainGridlinePaint(Color.black);
        XYItemRenderer renderer1 = plot.getRenderer();
        renderer1.setSeriesPaint( 0 , Color.BLUE );
        renderer1.setSeriesPaint( 1 , Color.RED);
        renderer1.setSeriesPaint( 2 , Color.BLACK);
        renderer1.setSeriesStroke(0, new BasicStroke(1.05f));
        renderer1.setSeriesStroke(1, new BasicStroke(1.05f));
        renderer1.setSeriesStroke(2, new BasicStroke(1.05f));
    }

    /**
     * Creates a combined chart.
     *
     * @return the combined chart.
     */
    private DemoPanel createDemoPanel() {

    	DemoPanel panel = new DemoPanel(new GridLayout(3, 2));
    	
    	
    	
        // create subplot 1...
        XYDataset data1 = createDatasetOzaBag("0.9");
        //final XYPlot subplot1 = new XYPlot(data1, null, rangeAxis1, renderer1);
        JFreeChart chart1 = ChartFactory.createXYLineChart("fadding factor = 0.9)", "", "Performance", data1, PlotOrientation.VERTICAL, false, true, false);
        setChartStyle((XYPlot) chart1.getPlot());
        
        
        
        XYDataset data3 = createDatasetOzaBag("0.99");
        //final XYPlot subplot1 = new XYPlot(data1, null, rangeAxis1, renderer1);
        JFreeChart chart3 = ChartFactory.createXYLineChart("fadding factor = 0.99)", "", "Performance", data3, PlotOrientation.VERTICAL, false, true, false);
        setChartStyle((XYPlot) chart3.getPlot());
        
                
        XYDataset data5 = createDatasetOzaBag("0.999");
        //final XYPlot subplot1 = new XYPlot(data1, null, rangeAxis1, renderer1);
        JFreeChart chart5 = ChartFactory.createXYLineChart("fadding factor = 0.999)", "", "Performance", data5, PlotOrientation.VERTICAL, false, true, false);
        setChartStyle((XYPlot) chart5.getPlot());
        
        
        
        panel.add(new ChartPanel(chart1));
        panel.add(new ChartPanel(chart3));
        panel.add(new ChartPanel(chart5));
        
        panel.addChart(chart1);
        panel.addChart(chart3);
        panel.addChart(chart5);
        
//        panel.addChart(chart2);
//        panel.addChart(chart3);
//        panel.addChart(chart4);

        panel.setPreferredSize(new Dimension(800, 600));
        return panel;

    }

    
    /**
     * Creates a sample dataset.
     *
     * @return Series 2.
     */
    private XYDataset createDatasetARF(String ff) {

        // create dataset 1...
        final XYSeries series1 = new XYSeries("recall-0");
        final XYSeries series2 = new XYSeries("recall-1");
        final XYSeries series3 = new XYSeries("g-mean");
        
        
        ArrayList<Double> recall0 = new ArrayList<>();
        ArrayList<Double> recall1 = new ArrayList<>();
        
        
        BufferedReader br = null;
        
        for(int i = 0; i < 5; i++){
        	
        	String ds = "fabric.arff(30-"+ff+"-2)WFLARF"+i+".csv";
        	try {
    			br = new BufferedReader(new FileReader("paramSettingsEval/" + ds));
    		} catch (FileNotFoundException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
        	
        	StringTokenizer strTok = null;
        	
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
						if(recall0.size() > ct){
							
							String recall0str = strTok.nextToken();
							String recall1str = strTok.nextToken();
							
							recall0.set(ct, (recall0.get(ct)+new Double(recall0str)));
							recall1.set(ct, (recall1.get(ct)+new Double(recall1str)));
							
						}else{
							String recall0str = strTok.nextToken();
							String recall1str = strTok.nextToken();
							
							recall0.add( new Double(recall0str));
							recall1.add(new Double(recall1str));
							
						}
						

					}
					ct++;
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        for(int i = 0; i < recall0.size()-1; i++){
        	series1.add(i, recall0.get(i)/5);
        	series2.add(i, recall1.get(i)/5);
        	series3.add(i, Math.sqrt((recall1.get(i)/5) * (recall0.get(i)/5)));
        }
        
        final XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series1);
        collection.addSeries(series2);
        collection.addSeries(series3);
        return collection;
        
    }
    
    /**
     * Creates a sample dataset.
     *
     * @return Series 2.
     */
    private XYDataset createDatasetOzaBag(String ff) {

        // create dataset 1...
        final XYSeries series1 = new XYSeries("recall-0");
        final XYSeries series2 = new XYSeries("recall-1");
        final XYSeries series3 = new XYSeries("g-mean");
        
        
        ArrayList<Double> recall0 = new ArrayList<>();
        ArrayList<Double> recall1 = new ArrayList<>();
        
        
        BufferedReader br = null;
        
        for(int i = 0; i < 5; i++){
        	
        	String ds = "fabric(10-"+ff+")WFLOzaBag"+i+".csv";
        	try {
    			br = new BufferedReader(new FileReader("RQ2/ParamEval/" + ds));
    		} catch (FileNotFoundException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
        	
        	StringTokenizer strTok = null;
        	
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
						if(recall0.size() > ct){
							
							String recall0str = strTok.nextToken();
							String recall1str = strTok.nextToken();
							
							recall0.set(ct, (recall0.get(ct)+new Double(recall0str)));
							recall1.set(ct, (recall1.get(ct)+new Double(recall1str)));
							
						}else{
							String recall0str = strTok.nextToken();
							String recall1str = strTok.nextToken();
							
							recall0.add( new Double(recall0str));
							recall1.add(new Double(recall1str));
							
						}
						

					}
					ct++;
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        for(int i = 0; i < recall0.size()-1; i++){
        	series1.add(i, recall0.get(i)/5);
        	series2.add(i, recall1.get(i)/5);
        	series3.add(i, Math.sqrt((recall1.get(i)/5) * (recall0.get(i)/5)));
        }
        
        final XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series1);
        collection.addSeries(series2);
        collection.addSeries(series3);
        return collection;
        
    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

    	final ChartParameterEval demo = new ChartParameterEval("CombinedDomainXYPlot Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}